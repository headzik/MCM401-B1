package at.fhooe.mcm.components.poi;

import java.awt.Image;
import java.awt.Polygon;

import at.fhooe.mcm.components.gis.GeoObject;

public class POIObject extends GeoObject {
	
	public static enum POI_TYPE {
		TYPE_1, TYPE_2, TYPE_POSITION
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
	public void setPosition(int x, int y) {
		this.setPoly(new Polygon(new int[]{(int)x},new int[]{(int)y}, 1));
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
