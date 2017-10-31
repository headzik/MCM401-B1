package at.fhooe.mcm.gps;

import java.io.FileNotFoundException;

import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIObject;

public class GPSModel extends Observable implements PositionUpdateListener {

	private NMEAParser mParser;
	private Thread t;
	private POIObject position;
	
	public GPSModel() {		
		try {
			 mParser = new NMEAParser("GPS-Log-III.log");
		} catch (FileNotFoundException _e1) {
			_e1.printStackTrace();
		}		
		
		System.out.println(GPSModel.lat2y(48.369484d));
		System.out.println(GPSModel.lon2x(14.512067d));
	}
	
	public NMEAParser getParser() {
		return mParser;
	}
	
	public void startParsing() {
		// Start parsing
		Thread t = (new Thread(mParser));
		t.start();
	}
	
	public void stopParsing() {
		if (t != null)
			t.interrupt();	}

	@Override
	public void updateSats(NMEAInfo _mInfo) {
		// Set position of poi object to new position (convert coordinates)
		
		// TODO --> CONVERT LONGITUDE AND LATITUDE TO SCREEN COORDINATES AND SET NEW POINT OF POSITION POI OBJECT.
		
		notifyObservers(position); // Notify that position has changed
	}
	
	public static final double lat2y(double aLat) {
	    return ((1 - Math.log(Math.tan(aLat * Math.PI / 180) + 1 / Math.cos(aLat * Math.PI / 180)) / Math.PI) / 2 * Math.pow(2, 0)) * 256 * 1000;
	}
	
	public static final double lon2x(double lon) {
	    return (lon + 180f) / 360f * 256f * 1000;
	}
}
