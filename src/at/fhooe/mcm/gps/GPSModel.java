package at.fhooe.mcm.gps;

import java.awt.Polygon;
import java.io.FileNotFoundException;

import at.fhooe.mcm.interfaces.IPositionUpdateListener;
import org.postgis.Point;

import at.fhooe.mcm.gis.DrawingContext;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIObject;
import at.fhooe.mcm.poi.POIServer;

public class GPSModel extends Observable implements IPositionUpdateListener {

	private NMEAParser mParser;
	private Thread t;
	private POIObject position;
	
	public GPSModel() {		
		try {
			 mParser = new NMEAParser("GPS-Log-III.log");
			 mParser.addListener(this);
		} catch (FileNotFoundException _e1) {
			_e1.printStackTrace();
		}		
	}
	
	public NMEAParser getParser() {
		return mParser;
	}
	
	public void startParsing() {
		// Start parsing
		t = (new Thread(mParser));
		t.start();
	}
	
	public void stopParsing() {
		if (t != null)
			t.interrupt();	
			position.setVisible(false);
		}

	@Override
	public void updateSats(NMEAInfo _mInfo) {
		// Set position of poi object to new position (convert coordinates)
		
		Point p = convertLatLong(_mInfo.getLatitude(), _mInfo.getLongitude());
		
		if (position == null) {
			position = new POIObject("0", DrawingContext.POI_TYPE, new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1), POIServer.loadImage("resources/position.png"), POIObject.POI_TYPE.TYPE_POSITION);
			position.setVisible(true);
		} else {
			position.setPoly(new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1));
		}		
		notifyObservers(position); // Notify that position has changed
	}
	
	private Point convertLatLong(double _lat, double _long) {
		GPSServer server = new GPSServer();
		return server.convertLatLong(_lat,_long);	
	}
}
