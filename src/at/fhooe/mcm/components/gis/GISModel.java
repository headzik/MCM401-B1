package at.fhooe.mcm.components.gis;

import javax.imageio.ImageIO;

import at.fhooe.mcm.components.poi.POIObject;
import at.fhooe.mcm.components.poi.POIServer;
import at.fhooe.mcm.components.poi.POIObject.POI_TYPE;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.interfaces.IDrawingContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class GISModel extends DataObservable {

    private final int ppi = 72;

    private int mWindowHeight;
    private int mWindowWidth;

    private Matrix mTransformationMatrix;

    private Vector<GeoObject> mObjects;
    private POIObject mPosition;

    private BufferedImage mImg;

    private boolean mDrawPOIs;
    private boolean mUseOSM;

    private IDrawingContext mDrawingContext;

    /**
     * Constructor initializing member variables.
     */
    public GISModel() {
        mWindowWidth = 640;
        mWindowHeight = 480;
        mImg = null;
        mTransformationMatrix = null;
        mDrawPOIs = true;
        mUseOSM = false;
        mObjects = new Vector<>();
    }

    public POIObject getPositionPOI() {
    	return mPosition;
    }
    
    public void positionUpdate(PositionContext _pc) {
    	if (mPosition == null) {			
			mPosition = new POIObject(Integer.toString(_pc.getID()), IDrawingContext.POI_TYPE, new Polygon(new int[] {(int) _pc.getPosition().x}, new int[] {(int) _pc.getPosition().y}, 1), POIServer.loadImage("resources/position.png"), POI_TYPE.TYPE_POSITION);
			mPosition.setVisible(true);
			addObject(mPosition);
    	} else {
    		mPosition.setPosition((int) _pc.getPosition().x, (int) _pc.getPosition().y);
    	}
    }
    
    public boolean containsObject(Object _o) {
    	return mObjects.contains(_o);
    }

    public void loadData(){
        GISServer osm = new GISServer();
        mObjects = osm.extractData();
        if (mObjects != null)
        	zoomToFit();
    }
    
    /**
     * Setter for mDrawPOIs.
     *
     * @param _set Set true to draw.
     */
    public void setPOIVisible(boolean _set) {
        mDrawPOIs = _set;
    }

    /**
     * Returns true if POIs are toggled visible, false if not
     *
     * @return True if POI active, false if not.
     */
    public boolean isPOIVisible() {
        return mDrawPOIs;
    }

    /**
     * Setter for mUserOSM variable.
     *
     * @param _use
     */
    public void useOSM(boolean _use) {
        mUseOSM = _use;
    }

    /**
     * Returns true if the application uses the OSM server.
     *
     * @return True if OSM used, false if not.
     */
    public boolean usesOSM() {
        return mUseOSM;
    }

    /**
     * Returns true if the mObjects vector is empty (=no objects to draw).
     *
     * @return True if mObjects empty, false if not.
     */
    public boolean objectsEmpty() {
        if (mObjects == null || mObjects.isEmpty())
            return true;
        return false;
    }

    public void addObject(Object _o){
        mObjects.add((GeoObject) _o);
    }

    /**
     * Resets the model to its initial state.
     */
    public void resetModel() {
        mImg = null;
        mTransformationMatrix = null;
        mObjects = null;
    }

    /**
     * Creates the polygon and draws it to a BufferdImage. Notifies observers that the polygon has changed.
     */
    public void drawPolygons() {

        BufferedImage img = new BufferedImage(mWindowWidth, mWindowHeight, BufferedImage.TYPE_INT_ARGB);
        // Draw polygons
        if (mTransformationMatrix != null && mObjects != null) {
            for (int i = 0; i < mObjects.size(); i++) {
                // Check if its a POI
                if (mObjects.get(i).getType() == IDrawingContext.POI_TYPE) {
                    // Is POI -> draw if visible
                    if (((at.fhooe.mcm.components.poi.POIObject) mObjects.get(i)).isVisible()) {
                        mDrawingContext.drawObject(mObjects.get(i), img.getGraphics(), mTransformationMatrix);
                    }
                } else {
                    // Is no POI -> draw
                    mDrawingContext.drawObject(mObjects.get(i), img.getGraphics(), mTransformationMatrix);
                }
            }
        }

        mImg = img;
        notifyObservers(mImg);
    }

    /**
     * Sets new window size.
     *
     * @param _width  Window width
     * @param _height Window height
     */
    public void setSize(int _width, int _height) {
        mWindowWidth = _width;
        mWindowHeight = _height;
        if (mImg != null)
            drawPolygons();
    }

    /**
     * Creates a transformation matrix to fit the polygons in the window size.
     */
    public void zoomToFit() {
        mTransformationMatrix = Matrix.zoomToFit(getMapBounds(mObjects), new Rectangle(0, 0, mWindowWidth - 1, mWindowHeight - 1));
    }

    /**
     * Creates a transformation matrix to zoom in with a given factor.
     *
     * @param _factor Zoomfactor
     */
    public void zoom(double _factor) {
        Point p = new Point(mWindowWidth / 2, mWindowHeight / 2);
        zoom(p, _factor);
    }

    /**
     * Creates a transformation matrix to zoom in to a given point.
     *
     * @param _pt     Point to zoom
     * @param _factor Zoomfactor
     */
    public void zoom(Point _pt, double _factor) {
        if (mTransformationMatrix != null)
            mTransformationMatrix = Matrix.zoomToPoint(mTransformationMatrix, _pt, _factor);
    }

    /**
     * Creates the smallest bounding box for all given polygons.
     *
     * @param _poly Given polygons
     * @return Bounding box
     */
    public Rectangle getMapBounds(Vector<GeoObject> _poly) {
        if (_poly == null)
            return null;

        Rectangle mapBounds = null;

        for (int i = 0; i < _poly.size(); i++) {
            if (mapBounds == null) {
                mapBounds = _poly.get(i).getBounds();
            } else {
                mapBounds = mapBounds.union(_poly.get(i).getBounds());
            }
        }

        return mapBounds;
    }

    /**
     * Creates a matrix to translate an object horizontal.
     *
     * @param _delta Translation value.
     */
    public void scrollHorizontal(int _delta) {
        if (mTransformationMatrix != null)
            mTransformationMatrix = Matrix.translate((double) _delta, 0).multiply(mTransformationMatrix);
    }

    /**
     * Creates a matrix to translate an object vertical.
     *
     * @param _delta Translation value.
     */
    public void scrollVertical(int _delta) {
        if (mTransformationMatrix != null)
            mTransformationMatrix = Matrix.translate(0, (double) _delta).multiply(mTransformationMatrix);
    }

    /**
     * Creates a matrix to rotate an object.
     *
     * @param _radiant Angle in radiants
     */
    public void rotate(double _radiant) {
        if (mTransformationMatrix != null) {

            Matrix translateToZero = Matrix.translate(-mWindowWidth / 2, -mWindowHeight / 2);
            Matrix rotate = Matrix.rotate(_radiant);
            Matrix translateBack = Matrix.translate(mWindowWidth / 2, mWindowHeight / 2);

            mTransformationMatrix = translateBack.multiply(rotate).multiply(translateToZero).multiply(mTransformationMatrix);
        }
    }

    /**
     * Returns a vector containing all GeoObjects which contain a given Point.
     *
     * @param _pt Given point.
     * @return Vector of objects
     */
    public Vector<GeoObject> initSelection(Point _pt) {
        Vector<GeoObject> objs = new Vector<GeoObject>();
        for (int i = 0; i < mObjects.size(); i++) {
            if (mObjects.get(i).getPoly().contains(_pt)) {
                objs.add(mObjects.get(i));
            }
        }

        return objs;
    }

    /**
     * Sets the transformation matrix to zoom in such a way that all objects fit in the given rectangle.
     *
     * @param _mapBounds Rectangle to fit in win coordinates
     */
    public void zoomRect(Rectangle _mapBounds) {
        Rectangle world = mTransformationMatrix.invers().multiply(_mapBounds);
        Rectangle win = new Rectangle(0, 0, mWindowWidth, mWindowHeight);
        mTransformationMatrix = Matrix.zoomToFit(world, win);
    }

    /**
     * Returns the world coordinates for a given point in window coordinates.
     *
     * @param _pt Given point to transform.
     * @return Transformed point
     */
    public Point getMapPoint(Point _pt) {
        return mTransformationMatrix.invers().multiply(_pt);
    }

    /**
     * Calculates the actual scale of the map.
     *
     * @return Scale
     */
    public long getScale() {
        GeoDoublePoint newPoint = new GeoDoublePoint(0, 1);
        newPoint = mTransformationMatrix.multiply(newPoint);
        double length = newPoint.length();

        long scale = (long) ((1 / length) * (ppi / 2.54));

        return scale;
    }

    /**
     * Returns the world coordinates for a given point in window coordinates.
     *
     * @param _pt Point in window coordinates
     * @return Point in world coordinates
     */
    public Point getWorldCoordinates(Point _pt) {
        Point world = mTransformationMatrix.invers().multiply(_pt);
        return world;
    }

    /**
     * Saves an image of the current shown map to the source directory.
     */
    public void saveImage() {
        File outputFile = new File(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date()) + ".png");
        try {
            ImageIO.write(mImg, "png", outputFile);
        } catch (IOException e) {
            System.out.println(">> An error occured when saving the image...");
        }
    }

    public void setDrawingContext(IDrawingContext drawingContext) {
        mDrawingContext = drawingContext;
    }
}
