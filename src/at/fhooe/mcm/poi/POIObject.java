package at.fhooe.mcm.poi;

import at.fhooe.mcm.gis.GeoObject;

import java.awt.Image;
import java.awt.Polygon;

public class POIObject extends GeoObject {
	
	public static enum POI_TYPE {
		TYPE_1, TYPE_2
	}
	
	private Image mImg;
	private POI_TYPE mType;
	private boolean mVisible;

	/**
	 * Constructor.
	 * @param _id Object id
	 * @param _type Object type
	 * @param _poly Polygon
	 * @param _img Image
	 */
	public POIObject(String _id, int _type, Polygon _poly, Image _img, POI_TYPE _poitype) {
		super(_id, _type, _poly);
		mImg = _img;
		mType = _poitype;
		mVisible = false;
	}
	
	/**
	 * Returns the mImg variable.
	 * @return mImg
	 */
	public Image getImage() {
		return mImg;
	}
	
	public void setVisible(boolean _visible) {
		mVisible = _visible;
	}
	
	public POI_TYPE getPOIType() {
		return mType;
	}
	
	public boolean isVisible() {
		return mVisible;
	}
}
