package at.fhooe.mcm.gps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;


public class GPSServer {
	
    private Connection mConn;

	public Point convertLatLong(double _lat, double _long) {
        try {
            System.out.println(">> Attempting to convert coordinates from OSM database...");
            // Load JDBC driver and establish connection
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/osm_austria";
            mConn = DriverManager.getConnection(url, "geo", "geo");
            // Add geometry types to the connection
            PGConnection c = (PGConnection) mConn;
            c.addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
            c.addDataType("box2d", (Class<? extends PGobject>) Class.forName("org.postgis.PGbox2d"));

            // Create statement and execute query
            Statement s = mConn.createStatement();

            ResultSet r;

            StringBuffer query = new StringBuffer();
        	query.append("SELECT ST_Transform(ST_SetSRID(ST_MakePoint(");
        	query.append(_long);
        	query.append(", ");
        	query.append(_lat);
        	query.append("), 4326), 3857);");
        	

            r = s.executeQuery(query.toString());
            r.next();
            
            PGgeometry geom = (PGgeometry) r.getObject("st_transform");
            String wkt =  geom.toString();
            Point pt = new Point(wkt);
            
            s.close();
            mConn.close();
            
            return pt;
        } catch (Exception _e) {
            System.out.println(">> Loading data failed!");
            return null;
        }	
	}
}
