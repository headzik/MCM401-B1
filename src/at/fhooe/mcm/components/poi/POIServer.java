package at.fhooe.mcm.components.poi;

import org.postgis.Geometry;
import org.postgis.LinearRing;
import org.postgis.PGgeometry;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.interfaces.IDrawingContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class POIServer {
    private Connection mConn;

    private Vector<POIObject> mObjects;

    public POIServer() {
        mObjects = new Vector<>();
        loadDataFromOSM();
    }

    /**
     * Loads the data from the OSM database.
     */
    @SuppressWarnings({"unchecked"})
    private void loadDataFromOSM() {
        try {
            System.out.println(">> POI Attempting to load data from OSM database...");
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


            // get fuel stations
            String query = "SELECT * FROM amenity_point AS a WHERE a.type IN (10005);";

            r = s.executeQuery(query);
            while (r.next()) {
                String id = r.getString("id");
                int type = r.getInt("type");
                PGgeometry geom = (PGgeometry) r.getObject("geom");

                switch (geom.getGeoType()) {
                    case Geometry.POINT:
                        String wkt = geom.toString();
                        org.postgis.Point p = new org.postgis.Point(wkt);
                        mObjects.add(new POIObject(id, IDrawingContext.POI_TYPE, new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1), loadImage("resources/gas.png"), POI_TYPE.TYPE_2));
                        break;
                    default:
                        break;
                }

            }


            // Get police stations
            query = "SELECT * FROM amenity_point AS a WHERE a.type IN (10003);";

            r = s.executeQuery(query);
            while (r.next()) {
                String id = r.getString("id");
                int type = r.getInt("type");
                PGgeometry geom = (PGgeometry) r.getObject("geom");

                switch (geom.getGeoType()) {
                    case Geometry.POINT:
                        String wkt = geom.toString();
                        org.postgis.Point p = new org.postgis.Point(wkt);
                        mObjects.add(new POIObject(id, IDrawingContext.POI_TYPE, new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1), loadImage("resources/police.png"), POI_TYPE.TYPE_1));
                        break;
                    default:
                        break;
                }

            }


            s.close();
            mConn.close();
        } catch (Exception _e) {
            System.out.println(">> Loading data failed!");
        }

    }

    public static BufferedImage loadImage(String _s) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(_s);
            return ImageIO.read(input);
        } catch(IOException _ex) {
            System.out.println("Error while loading pic");
        }
        return null;
    }

    public Vector<POIObject> extractData() {
        return mObjects;
    }
}
