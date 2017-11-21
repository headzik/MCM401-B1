package at.fhooe.mcm.components.gis;

import org.postgis.Geometry;
import org.postgis.LinearRing;
import org.postgis.PGgeometry;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class GISServer {

    private Connection mConn;

    private Vector<GeoObject> mObjects;

    public GISServer() {
        mObjects = new Vector<>();
        loadDataFromOSM();
    }

    /**
     * Loads the data from the OSM database.
     */
    @SuppressWarnings({"unchecked"})
    private void loadDataFromOSM() {
        try {
            System.out.println(">> Attempting to load data from OSM database...");
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

            // Get boundary types
            String query = "SELECT * FROM boundary_area AS a WHERE a.type IN (8001, 8002, 8004);";

            r = s.executeQuery(query);
            while (r.next()) {
                String id = r.getString("id");
                int type = r.getInt("type");
                PGgeometry geom = (PGgeometry) r.getObject("geom");

                switch (geom.getGeoType()) {
                    case Geometry.POLYGON:
                        String wkt = geom.toString();
                        org.postgis.Polygon p = new org.postgis.Polygon(wkt);
                        if (p.numRings() >= 1) {
                            Polygon poly = new Polygon();
                            LinearRing ring = p.getRing(0);
                            for (int i = 0; i < ring.numPoints(); i++) {
                                org.postgis.Point pPG = ring.getPoint(i);
                                poly.addPoint((int) pPG.x, (int) pPG.y);
                            }
                            mObjects.add(new GeoObject(id, type, poly));
                        }
                        break;
                    default:
                        break;
                }

            }

         /*   // Get landuse types
            query = "SELECT * FROM landuse_area AS a WHERE a.type IN (5001, 5002, 5003, 5004, 5006);";
            r = s.executeQuery(query);

            while (r.next()) {
                String id = r.getString("id");
                int type = r.getInt("type");
                PGgeometry geom = (PGgeometry) r.getObject("geom");

                switch (geom.getGeoType()) {
                    case Geometry.POLYGON:
                        String wkt = geom.toString();
                        org.postgis.Polygon p = new org.postgis.Polygon(wkt);
                        if (p.numRings() >= 1) {
                            Polygon poly = new Polygon();
                            LinearRing ring = p.getRing(0);
                            for (int i = 0; i < ring.numPoints(); i++) {
                                org.postgis.Point pPG = ring.getPoint(i);
                                poly.addPoint((int) pPG.x, (int) pPG.y);
                            }
                            mObjects.add(new GeoObject(id, type, poly));
                        }
                        break;
                    default:
                        break;
                }
            }

            // Get natural types
            query = "SELECT * FROM natural_area AS a WHERE a.type IN (6001, 6002, 6005);";

            r = s.executeQuery(query);

            while (r.next()) {
                String id = r.getString("id");
                int type = r.getInt("type");
                PGgeometry geom = (PGgeometry) r.getObject("geom");

                switch (geom.getGeoType()) {
                    case Geometry.POLYGON:
                        String wkt = geom.toString();
                        org.postgis.Polygon p = new org.postgis.Polygon(wkt);
                        if (p.numRings() >= 1) {
                            Polygon poly = new Polygon();
                            LinearRing ring = p.getRing(0);
                            for (int i = 0; i < ring.numPoints(); i++) {
                                org.postgis.Point pPG = ring.getPoint(i);
                                poly.addPoint((int) pPG.x, (int) pPG.y);
                            }
                            mObjects.add(new GeoObject(id, type, poly));
                        }
                        break;
                    default:
                        break;
                }
            }
            */

            s.close();
            mConn.close();
        } catch (Exception _e) {
            System.out.println(">> Loading data failed!\n" + _e.toString());
        }

    }

    public Vector<GeoObject> extractData() {
        return mObjects;
    }
}
