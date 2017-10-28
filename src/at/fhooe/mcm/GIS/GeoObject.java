package at.fhooe.mcm.GIS;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * The class is a first very "rudimentary" representation 
 * of a geo object
 */
public class GeoObject {

  /// the id of the object
  protected String  m_id   = null;
  /// the type of the object
  protected int     m_type = 0;
  /// the geometry of the object
  protected Polygon m_geo  = null;
  
  /**
   * Constructor
   * 
   * @param _id The id of the object
   * @param _type the type of the object
   * @param _poly the geometry of the object
   */
  public GeoObject(String _id, int _type, Polygon _poly) {
    m_id   = _id;
    m_type = _type;
    m_geo  = _poly;
  }
  
  /**
   * Returns the ID of the Object
   *
   * @return the ID of the Object
   *
   * @see java.lang.String
   */
  public String getId()   { return m_id; }
  
  /**
   * Returns the type (river, wood, ...) of the object
   *
   * @return the type of the object
   */
  public int    getType() { return m_type; }
  
  /**
   * Returns the geometry of the object (in this case only area type objects are supported
   * 
   * @return the geometry of the object
   * @see java.awt.Polygon
   */
  public Polygon getGeometry() { return m_geo; }
  
  /**
   * Returns the minimum bounding box of the object
   *
   * @return the minimium bounding box of the object in form of a rectangle
   * @see java.awt.Rectangle
   */
  public Rectangle getBounds() {
  	if (m_geo != null) {
  		return m_geo.getBounds();
  	}
  	return null;
  }
  
  /**
   * Checks if a given point (world coordinate system) lies inside the object
   *
   * @return true, if the point is inside the object
   * @see java.awt.Point
   */
  public boolean contains(Point _pt) {
  	return m_geo.contains(_pt);
  }
  
  
  /**
   * The object can use the given drawing context to paint (resp. represent) itself
   * 
   * @param _g a drawing context used to paint the object
   *
   * @param _matrix the current transformation matrix used to transfer the world coordinate 
   *                system based points of the object into the window coordinate based coordinate 
   *                space
   *
   * @see java.awt.Graphics
   * @see GeoTransformationsmatrix
   */
  public void paint(Graphics _g, GeoTransformationMatrix _matrix) {
    if (m_geo != null && _g != null && _matrix != null)
      _g.drawPolygon(_matrix.multiply(m_geo));
  }
  
  /**
   * Returns the internal information of the object in form of an string
   *
   * @return a string representation of the object
   */
  public String toString() {
  	return "OBJ[ " + m_id + " ;\t " + m_type + "\t POLY ]";
  }
}