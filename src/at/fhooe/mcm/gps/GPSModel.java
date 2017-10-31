package at.fhooe.mcm.gps;

import java.awt.Polygon;
import java.io.FileNotFoundException;

import org.postgis.Point;

import at.fhooe.mcm.gis.DrawingContext;
import at.fhooe.mcm.gis.GeoDoublePoint;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIObject;
import at.fhooe.mcm.poi.POIServer;

public class GPSModel extends Observable implements PositionUpdateListener {

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
			t.interrupt();	}

	@Override
	public void updateSats(NMEAInfo _mInfo) {
		// Set position of poi object to new position (convert coordinates)
		int x = (int) degreeToMeter(_mInfo.getLatitude());
		int y = (int) degreeToMeter(_mInfo.getLongitude());
		
		Point p = new Point(x, y); // TODO --> convert lat & long to x & y	
		
		if (position == null) {
			position = new POIObject("0", DrawingContext.POI_TYPE, new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1), POIServer.loadImage("resources/position.png"), POIObject.POI_TYPE.TYPE_POSITION);
			position.setVisible(true);
		} else {
			position.setPoly(new Polygon(new int[]{(int) p.getX()},new int[]{(int) p.getY()},1));
		}		
		notifyObservers(position); // Notify that position has changed
	}
	
	private double degreeToMeter (float _deg) {
		double meter = Math.log10((Math.tan((90.0 + _deg) * Math.PI / 360.0)) / (Math.PI / 180.0))*111319.490778d;
		return meter;
	}
}
