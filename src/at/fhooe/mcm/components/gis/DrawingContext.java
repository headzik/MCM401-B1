package at.fhooe.mcm.components.gis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;

import at.fhooe.mcm.components.poi.POIObject;


public class DrawingContext {
	
	public static final int POI_TYPE = 999;
	
	// Landuse types
	public static final int LANDUSE_RESIDENTIAL = 5001;
	public static final int LANDUSE_INDUSTRIAL = 5002;
	public static final int LANDUSE_COMMERCIAL = 5003;
	public static final int LANDUSE_FOREST = 5004;
	public static final int LANDUSE_MEADOW = 5006;
	
	// Natural types
	public static final int NATURAL_GRASSLAND = 6001;
	public static final int NATURAL_WOOD = 6002;
	public static final int NATURAL_WATER = 6005;
	
	
	/**
	 * Method for drawing an object to the map.
	 * @param _obj Object to draw
	 * @param _g Graphics context to draw on
	 * @param _matrix Transformationmatrix to apply
	 */
	public static void drawObject(GeoObject _obj, Graphics _g, Matrix _matrix) {	
		Polygon p = _matrix.multiply(_obj.getPoly());
		
		switch (_obj.getType()) {
		case 233:
			_g.setColor(Color.WHITE);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);
			break;
		case 931:
			_g.setColor(Color.RED);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);
			break;
		case 932:
			_g.setColor(Color.ORANGE);
			_g.fillPolygon(p);
			_g.setColor(Color.RED);
			_g.drawPolygon(p);
			break;
		case 1101:
			_g.setColor(Color.MAGENTA);
			_g.fillPolygon(p);
			_g.setColor(Color.GREEN);
			_g.drawPolygon(p);
			break;
		case POI_TYPE:
			// POIObject
			Image img = ((POIObject) _obj).getImage();
			_g.drawImage(img, p.getBounds().x - img.getWidth(null), p.getBounds().y - img.getHeight(null), null, null);
			break;	
			
		case LANDUSE_COMMERCIAL:
			_g.setColor(Color.LIGHT_GRAY);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case LANDUSE_FOREST:
			_g.setColor(Color.GREEN);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case LANDUSE_INDUSTRIAL:
			_g.setColor(Color.LIGHT_GRAY);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case LANDUSE_MEADOW:
			_g.setColor(Color.GREEN);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case LANDUSE_RESIDENTIAL:
			_g.setColor(Color.GRAY);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case NATURAL_GRASSLAND:
			_g.setColor(Color.GREEN);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case NATURAL_WATER:
			_g.setColor(Color.BLUE);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
		case NATURAL_WOOD:
			_g.setColor(Color.GREEN);
			_g.fillPolygon(p);
			_g.setColor(Color.BLACK);
			_g.drawPolygon(p);	
			break;
			default:
				_g.setColor(Color.BLACK);
				_g.drawPolygon(p);		
		}
		_g.dispose();
	}
}
