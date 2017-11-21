package at.fhooe.mcm.components.gis;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;

public class Matrix {

	private double[][] mMatrix;
	
	/**
	 * Standardconstructor. Initialize matrix with size 3x3.
	 */
	public Matrix() {
		mMatrix = new double[3][3];
	}
	
	public Matrix(double[][] _matrix) {
		mMatrix = _matrix;
	}
	
	/**
	 * Initializes 3x3 matrix with given parameters.
	 */
	public Matrix(double _m11, double _m12, double _m13,
				  double _m21, double _m22, double _m23,
				  double _m31, double _m32, double _m33) {
		
		mMatrix = new double[3][3];
		mMatrix[0][0] = _m11;
		mMatrix[0][1] = _m12;
		mMatrix[0][2] = _m13;
		mMatrix[1][0] = _m21;
		mMatrix[1][1] = _m22;
		mMatrix[1][2] = _m23;
		mMatrix[2][0] = _m31;
		mMatrix[2][1] = _m32;
		mMatrix[2][2] = _m33;	
	}
	
	/**
	 * Returns string representation of matrix.
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();		
		for (int i = 0; i < mMatrix.length; i++) {
			for (int j = 0; j < mMatrix[0].length; j++) {
				buf.append(mMatrix[i][j] + "\t");
			}
			buf.append("\n");
		}		
		return buf.toString();
	}
	
	/**
	 * Returns the inverted matrix of the actual matrix.
	 * @return Inverted matrix.
	 */
	public Matrix invers() {
		
		double det = mMatrix[0][0] * mMatrix[1][1] * mMatrix[2][2] +
					 mMatrix[0][1] * mMatrix[1][2] * mMatrix[2][0] + 
					 mMatrix[0][2] * mMatrix[1][0] * mMatrix[2][1] -
					 mMatrix[0][0] * mMatrix[1][2] * mMatrix[2][1] -
					 mMatrix[0][1] * mMatrix[1][0] * mMatrix[2][2] - 
					 mMatrix[0][2] * mMatrix[1][1] * mMatrix[2][0];
		
		return new Matrix((1/det)*(mMatrix[1][1] * mMatrix[2][2] - mMatrix[1][2] * mMatrix[2][1]),
				          (1/det)*(mMatrix[0][2] * mMatrix[2][1] - mMatrix[0][1] * mMatrix[2][2]),
				          (1/det)*(mMatrix[0][1] * mMatrix[1][2] - mMatrix[0][2] * mMatrix[1][1]),
				          (1/det)*(mMatrix[1][2] * mMatrix[2][0] - mMatrix[1][0] * mMatrix[2][2]),
				          (1/det)*(mMatrix[0][0] * mMatrix[2][2] - mMatrix[0][2] * mMatrix[2][0]),
				          (1/det)*(mMatrix[0][2] * mMatrix[1][0] - mMatrix[0][0] * mMatrix[1][2]),
				          (1/det)*(mMatrix[1][0] * mMatrix[2][1] - mMatrix[1][1] * mMatrix[2][0]),
				          (1/det)*(mMatrix[0][1] * mMatrix[2][0] - mMatrix[0][0] * mMatrix[2][1]),
				          (1/det)*(mMatrix[0][0] * mMatrix[1][1] - mMatrix[0][1] * mMatrix[1][0]));	
	}
	
	/**
	 * Returns a matrix, containing the result of a matrix multiplication of the actual and another given matrix.
	 * @param _other Matrix to multiply with
	 * @return Result matrix of multiplication
	 */
	public Matrix multiply(Matrix _other) {
		
		/* Check input parameters. */
		if (this.getMatrix().length != _other.getMatrix()[0].length || this.getMatrix() == null || _other.getMatrix() == null)
			return null; // error

		double[][] result = new double[this.getMatrix().length][_other.getMatrix()[0].length];
		
		for (int i = 0; i < this.getMatrix().length; i++) { // rows
			for (int j = 0; j < _other.getMatrix()[0].length; j++) { // columns
				for (int k = 0; k < this.getMatrix()[0].length; k++) { // sum
					result[i][j] += this.getMatrix()[i][k] * _other.getMatrix()[k][j];
				}
			}
		}
		
		Matrix m = new Matrix(result);
		return m;
	}
	
	/**
	 * Returns a point, containing the result of the multiplication of the point with the actual matrix.
	 * @param _pt Point to multiply with
	 * @return Resulting point
	 */
	public Point multiply(Point _pt) {
		if (getMatrix() != null && _pt != null) {
			Point p = new Point((int) (getMatrix()[0][0] * _pt.getX() +getMatrix()[0][1] * _pt.getY() + getMatrix()[0][2]),
					  			(int) (getMatrix()[1][0] * _pt.getX() + getMatrix()[1][1] * _pt.getY() + getMatrix()[1][2]));
			return p;			
		} else {
			return null;
		}
	}
	
	/**
	 * Multiplies a given rectangle with the actual matrix and returns the result of the multiplication.
	 * @param _rect Given rectangle
	 * @return new Rectangle after multiplication
	 */
	public Rectangle multiply(Rectangle _rect) {
		
		Point upperLeft = new Point();
		Point lowerRight = new Point();
		upperLeft.setLocation(_rect.x, _rect.y);
		lowerRight.setLocation(_rect.x + _rect.getWidth(),
				   _rect.y + _rect.getHeight());
		
		Point newUpperLeft = multiply(upperLeft);
		Point newLowerRight = multiply(lowerRight);

		Rectangle r = new Rectangle(newUpperLeft);	
		r.add(newLowerRight);
		return r;
	}
	
	/**
	 * Multiplies a given polygon with the actual matrix and returns the result of the multiplication.
	 * @param _poly Given polygon
	 * @return Polygon after multiplication
	 */
	public Polygon multiply(Polygon _poly) {	
		Polygon newPoly = new Polygon();	
		for (int i = 0; i < _poly.npoints; i++) {
			Point p = new Point((int)_poly.xpoints[i], (int)_poly.ypoints[i]);
			p = multiply(p);
			newPoly.addPoint((int)p.x, (int)p.y);
		}	
		return newPoly;
	}
	
	/**
	 * Returns the actual matrix array.
	 * @return Matrix
	 */
	public double[][] getMatrix() {
		return mMatrix;
	}
	
	/**
	 * Returns a translation matrix.
	 * @param _x Translation on x-axis
	 * @param _y Translation on y-axis
	 * @return Translation matrix
	 */
	public static Matrix translate(double _x, double _y) {
		return new Matrix(1, 0, _x, 0, 1 , _y, 0, 0, 1);
	}
	
	/**
	 * Returns a translation matrix.
	 * @param _pt Point containing translation data
	 * @return Translation matrix
	 */
	public static Matrix translate(Point _pt) {
		if (_pt == null)
			return null;
		return new Matrix(1, 0, _pt.getX(), 
						  0, 1 , _pt.getY(), 
						  0, 0, 1);
	}
	
	/**
	 * Returns a scale matrix.
	 * @param _scaleVal Scale value
	 * @return Scale matrix
	 */
	public static Matrix scale(double _scaleVal) {
		return new Matrix(_scaleVal, 0, 0,
						  0, _scaleVal, 0,
						  0, 0, 1);
	}
	
	/**
	 * Returns a mirror matrix (x-axis).
	 * @return Mirror matrix
	 */
	public static Matrix mirrorX() {
		return new Matrix(1, 0, 0, 
					      0, -1, 0, 
					      0, 0, 1);
	}
	
	/**
	 * Returns a mirror matrix (y-axis).
	 * @return Mirror matrix
	 */
	public static Matrix mirrorY() {
		return new Matrix(-1, 0, 0,
						  0, 1, 0,
						  0, 0, 1);
	}
	
	/**
	 * Returns a rotation matrix.
	 * @param _alpha in radians.
	 * @return Rotation matrix
	 */
	public static Matrix rotate(double _alpha) {
		return new Matrix(Math.cos(_alpha), -Math.sin(_alpha), 0,
						  Math.sin(_alpha), Math.cos(_alpha), 0,
						  0, 0, 1);
	}

	/**
	 * Returns the factor which is required to scale the world rectangle in the win rectangle
	 * in relation to the width (x-axis).
	 * @param _world World rectangle
	 * @param _win Window rectangle
	 * @return Zoom factor
	 */
	public static double getZoomFactorX(Rectangle _world, Rectangle _win) {
		return (_win.getWidth() / _world.getWidth());
	}
	
	/**
	 * Returns the factor which is required to scale the world rectangle in the win rectangle
	 * in relation to the height (y-axis).
	 * @param _world World rectangle
	 * @param _win Window rectangle
	 * @return Zoom factor
	 */
	public static double getZoomFactorY(Rectangle _world, Rectangle _win) {
		return (_win.getHeight() / _world.getHeight());
	}
	
	/**
	 * Returns a matrix containing all necessary information to display a _world rect in a _display rect.
	 * @param _world Rectangle in world coordinates
	 * @param _win Rectangle in window coordinates
	 * @return Transformation matrix	
	 */
	public static Matrix zoomToFit(Rectangle _world, Rectangle _win) {
		// Translate to origin
		Matrix translateToOrigin = translate(-_world.getCenterX(), -_world.getCenterY());
		// Scale
		Matrix scaleMatrix;
		if (getZoomFactorX(_world, _win) < getZoomFactorY(_world, _win)) {
			scaleMatrix = scale(getZoomFactorX(_world, _win));
		} else {
			scaleMatrix = scale(getZoomFactorY(_world, _win));
		}
		// Mirror on x Axis
		Matrix mirrorX = mirrorX();
		// Translate to original position
		Matrix translateToOriginalPos = translate(_win.getCenterX(), _win.getCenterY());
		
		// Result
		return translateToOriginalPos.multiply(mirrorX).multiply(scaleMatrix).multiply(translateToOrigin);
	}
	
	/**
	 * Returns a matrix containing all necessary information to zoom a specific point in or out.
	 * @param _old Matrix to operate
	 * @param _zoomPt Point to zoom	
	 * @param _zoomScale Zoom scale
	 * @return Transformation matrix
	 */
	public static Matrix zoomToPoint(Matrix _old, Point _zoomPt, double _zoomScale) {
		Matrix translateToZero = translate(-_zoomPt.x, -_zoomPt.y);
		Matrix scale = scale(_zoomScale);
		Matrix translateToOrigin = translate(_zoomPt.x, _zoomPt.y);
		
		return translateToOrigin.multiply(scale).multiply(translateToZero).multiply(_old);
	}
	
	/**
	 * Test method for zoomToFit method.
	 */
	public void testZTF() {
		Rectangle world = new Rectangle(47944531, 608091485, 234500, 213463);
		Rectangle win = new Rectangle(0, 0, 640, 480);
		
		Matrix x = zoomToFit(world, win);
		Matrix inverted = x.invers();
		
		System.out.println(inverted.toString());
		
		Rectangle result = x.multiply(world);
		System.out.println("Result: " + result.toString());
		
		System.out.println("Reversed: " + inverted.multiply(win));
	}
	
	public GeoDoublePoint multiply(GeoDoublePoint _pt) {
		double srcx = _pt.mX;
		double srcy = _pt.mY;
		double destx = mMatrix[0][0] * srcx + mMatrix[0][1] * srcy;
		double desty = mMatrix[1][0] * srcx + mMatrix[1][1] * srcy;
		return new GeoDoublePoint(destx, desty);
	}
}
