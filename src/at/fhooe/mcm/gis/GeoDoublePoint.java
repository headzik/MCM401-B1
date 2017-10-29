package at.fhooe.mcm.gis;

public class GeoDoublePoint {
	
	public double mX,mY;
	
	/**
	 * Constructor.
	 * @param _x x-Coordinate
	 * @param _y y-Coordinate
	 */
	public GeoDoublePoint(double _x, double _y) {
		mX = _x;
		mY = _y;
	}
	
	/**
	 * Calculates the vector length from the origin to the point.
	 * @return Length of the vector.
	 */
	public double length() {
		return Math.sqrt(Math.pow(mX, 2) + Math.pow(mY,2));
	}
	
	/**
	 * Returns the point as string.
	 * @return Point as string.
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("X: " + mX + ", " + "Y: " + mY + "\n");
		return buf.toString();
	}

}
