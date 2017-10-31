package at.fhooe.mcm.gis;

import java.awt.Polygon;
import java.awt.Rectangle;


public class GeoObject extends Object{

	private String mID;
	private int mType;
	private Polygon mPolygon;
	
	/**
	 * Constructor initializing object.
	 * @param _id ID of the object
	 * @param _type Type of the object
	 * @param _poly Polygon
	 */
	public GeoObject(String _id, int _type, Polygon _poly) {
		mID = _id;
		mType = _type;
		mPolygon = _poly;
	}
	
	public void setPoly(Polygon _poly) {
		if (_poly != null)
			mPolygon = _poly;
	}
	
	/**
	 * Returns object id.
	 * @return Object ID
	 */
	public String getID() {
		return mID;
	}
	
	/**
	 * Returns object type.
	 * @return Object Type
	 */
	public int getType() {
		return mType;
	}
	
	/**
	 * Returns object polygon.
	 * @return Object polygon
	 */
	public Polygon getPoly() {
		return mPolygon;
	}
	
	/**
	 * Returns the bounds of the object polygon.
	 * @return Rectangle containing bounds.
	 */
	public Rectangle getBounds() {
		return mPolygon.getBounds();
	}
	
	/**
	 * Returns object as String.
	 */
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Object ID: " + mID + "\n");
		b.append("Object Type: " + mType + "\n");
		b.append("Object X: " + getBounds().x + ", ");
		b.append("Object Y: " + getBounds().y + ", ");
		b.append("Object Width: " + getBounds().getWidth() + ", ");
		b.append("Object Height: " + getBounds().getHeight() + "\n");
		return b.toString();
	}
}
