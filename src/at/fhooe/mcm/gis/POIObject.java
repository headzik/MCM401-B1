package at.fhooe.mcm.gis;

import java.awt.Image;
import java.awt.Polygon;

public class POIObject extends GeoObject {
	
	private Image mImg;

	/**
	 * Constructor.
	 * @param _id Object id
	 * @param _type Object type
	 * @param _poly Polygon
	 * @param _img Image
	 */
	public POIObject(String _id, int _type, Polygon _poly, Image _img) {
		super(_id, _type, _poly);
		mImg = _img;
	}
	
	/**
	 * Returns the mImg variable.
	 * @return mImg
	 */
	public Image getImage() {
		return mImg;
	}
}
