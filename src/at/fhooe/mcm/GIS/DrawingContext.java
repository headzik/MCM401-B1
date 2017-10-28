package at.fhooe.mcm.GIS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class DrawingContext {

  /**
   * Set the color values for the geo objects based on their type
   * rivers --> blue
   * woods --> green
   * ... --> ... 
   */
  public static void drawObject(GeoObject _obj, 
  								Graphics  _g, 
  								GeoTransformationMatrix _matrix) {
  	if (_obj != null && _g != null && _matrix != null) {
  		Polygon poly = _obj.getGeometry();
  		if (poly != null) {
  			poly = _matrix.multiply(poly);
	  	  switch (_obj.getType()) {
	  	    case 1112 : { // Bundeslaender
	  	    	_g.setColor(new Color(211,255,190));
	  	    	_g.fillPolygon(poly);
	  	    	_g.setColor(new Color(255,127,127));
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    case 233  : {
	  	      _g.setColor(Color.white);
	  	      _g.fillPolygon(poly);
	  	      _g.setColor(Color.black);
	  	      _g.drawPolygon(poly);
	  	    } break;
	  	    case 666 : {
	  	    	_obj.paint(_g, _matrix);
	  	    } break;
	  	    case 931  : {
	  	    	_g.setColor(Color.red);
	  	    	_g.fillPolygon(poly);
	  	    	_g.setColor(Color.black);
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    case 932  : {
	  	    	_g.setColor(Color.orange);
	  	    	_g.fillPolygon(poly);
	  	    	_g.setColor(Color.red);
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    case 933  : {
	  	    	_g.setColor(Color.black);
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    case 934  : {
	  	    	_g.setColor(Color.black);
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    case 1101 : {
	  	    	_g.setColor(Color.magenta);
	  	    	_g.fillPolygon(poly);
	  	    	_g.setColor(Color.green);
	  	    	_g.drawPolygon(poly);
	  	    } break;
	  	    default : {
	  	    	_g.setColor(Color.black);
	  	    	_g.drawPolygon(poly);
	  	    }
	  	  } // switch
	  	} else {
	  		System.out.println("poly is null");
	  	}
  	} else {
  	  System.out.println("encountered null object");
  	}									 
  }
}