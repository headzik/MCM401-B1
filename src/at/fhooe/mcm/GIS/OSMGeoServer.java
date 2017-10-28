package at.fhooe.mcm.GIS;

import java.awt.Point;
import java.awt.Polygon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.postgis.Geometry;
import org.postgis.LinearRing;
import org.postgis.PGgeometry;
import org.postgresql.PGConnection;

public class OSMGeoServer {
	//		ResultSet r = s.executeQuery("SELECT * FROM amenity_austria_points AS a WHERE a.geom && ST_MakeEnvelope(14.3815, 48.2054, 14.3817, 48.2056)"); 

	// lb java.awt.Point[x=14084774,y=48132875]
	// rt java.awt.Point[x=14540614,y=48448764]
	
	private Connection mConn;
	
	public static final String[] mQueries = {
		//"SELECT * FROM boundary_area;",
		//"SELECT * FROM landuse_line;",
		"SELECT * FROM natural_area;",
		//"SELECT * FROM place_area;",
		"SELECT * FROM amenity_area;",
		"SELECT * FROM leisure_area ;",
		//"SELECT * FROM waterway_area;",
		"SELECT * FROM waterway_line ;",
		"SELECT * FROM building_area ;",
		"SELECT * FROM highway_area ;",
		"SELECT * FROM highway_line ;",
		"SELECT * FROM railway_line ;"
	};
	
	public static final String USER = "geo";
	public static final String PASS = "geo";
	public static final String CONN = "jdbc:postgresql://localhost:5432/osm";
	
	private static final int OFFSET = 100000;

	/**
	 * 
	 * @param _user geo
	 * @param _pass geo
	 * @param _con  jdbc:postgresql://localhost:5432/OSM_Austria
	 * @return true, if connection established
	 */
	public boolean login(String _user, String _pass, String _con) {
	    try {
			Class.forName("org.postgis.DriverWrapper");
			mConn = DriverManager.getConnection(_con, _user, _pass);
		    /* Add the geometry types to the connection. */
		    PGConnection c = (PGConnection) mConn;
		    c.addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
		    c.addDataType("box2d",    Class.forName("org.postgis.PGbox2d"));
	    } catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	    return true;
	}
	
	public Vector<GeoObject> getData(String[] _queries) {
		System.out.print("loading data ... ");
		Vector<GeoObject> data = new Vector<GeoObject>();
		Statement         s    = null;
		try {
			s = mConn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		for (String query : _queries) {
		    try {
				ResultSet r = s.executeQuery(query); 
				while( r.next() ) { 
					try {
					    String     id     = r.getString("id"); 
					    int        type   = r.getInt("type");
					    PGgeometry geom   = (PGgeometry)r.getObject("geom"); 
					    switch (geom.getGeoType()) {
					    case Geometry.POINT :        {
		//			    	System.out.println("found object ("+ id + ") Point ... ");
						    Geometry  g  = geom.getGeometry();	  					    
						    org.postgis.Point pPG = g.getPoint(0);
						    Point     pt = new Point((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
					    } break;
					    case Geometry.LINESTRING :   {
		//			    	System.out.println("found object ("+ id + ") Line ... ");
						    Geometry  g    = geom.getGeometry();	  					    
						    Point[]   pts  = new Point[g.numPoints()];
						    for (int i = 0; i < g.numPoints(); i++) {
						    	org.postgis.Point pPG = g.getPoint(i);
						    	pts[i] = new Point((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
						    }
					    } break;
					    case Geometry.POLYGON :      {
		//			    	System.out.println("found object ("+ id + ") Polygon ... ");
							String              wkt = geom.toString();
							org.postgis.Polygon p   = new org.postgis.Polygon(wkt);
							if (p.numRings() > 1) {					
							    Polygon    poly = new Polygon();
							    // Ring 0 --> main polygon ... rest should be holes
							    LinearRing ring = p.getRing(0);
							    for (int i = 0; i < ring.numPoints(); i++) {
							    	org.postgis.Point pPG = ring.getPoint(i);
							    	poly.addPoint((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
							    }
							    GeoObject obj = new GeoObject(id, type, poly);
							    data.add(obj);
								// extract the holes
								for (int j = 1; j < p.numRings(); j++) {
									ring = p.getRing(j);
									poly = new Polygon();
								    for (int k = 0; k < ring.numPoints(); k++) {
								    	org.postgis.Point pPG = ring.getPoint(k);
								    	poly.addPoint((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
								    } // for points
								} // for holes
		
							} else { // poly without holes
							    Polygon   poly = new Polygon();
							    for (int i = 0; i < p.numPoints(); i++) {
							    	org.postgis.Point pPG = p.getPoint(i);
							    	poly.addPoint((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
							    }
							    GeoObject obj = new GeoObject(id, type, poly);
							    data.add(obj);
							} // poly with or without holes
		
					    } break;
					    case Geometry.MULTIPOLYGON : {
		//			    	System.out.println("found object ("+ id + ") MultiPolygon ... ");
							String                   wkt = geom.toString();
							org.postgis.MultiPolygon mp  = new org.postgis.MultiPolygon(wkt);
							for (org.postgis.Polygon p : mp.getPolygons()) {			
								// iterate over exclaves
								// first exclave is primary poly
								// next  exclaves are secondary poly
							    Polygon    poly = new Polygon();
							    // first ring is exterior polygonal shape
							    LinearRing ring = p.getRing(0);
							    for (int i = 0; i < ring.numPoints(); i++) {
							    	org.postgis.Point pPG = ring.getPoint(i);
							    	poly.addPoint((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
							    }
							    // next rings are holes
								// extract the holes
								for (int j = 1; j < p.numRings(); j++) {
									ring = p.getRing(j);
									poly = new Polygon();
								    for (int k = 0; k < ring.numPoints(); k++) {
								    	org.postgis.Point pPG = ring.getPoint(k);
								    	poly.addPoint((int)(pPG.x * OFFSET), (int)(pPG.y*OFFSET));
								    }
								} // for all holes
							} // for all exclaves/parts
					    } break;
					    } // switch geotype
					} catch (Exception _e) {
						_e.printStackTrace();
					}
				} // while resultset
			} catch (SQLException _e) {
				_e.printStackTrace();
			} 
		} // for query
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
