package at.fhooe.mcm.components.gps;

import java.io.FileNotFoundException;

import org.postgis.Point;

import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.PositionContext.PositionType;
import at.fhooe.mcm.interfaces.IPositionUpdateListener;
import at.fhooe.mcm.objects.Observable;

/**
 * The model for the GPS component
 * @author ifumi
 *
 */
public class GPSModel extends Observable implements IPositionUpdateListener {

	private NMEAParser mParser;
	private Thread t;
	
	private int mPositionID;
	
	/**
	 * Constructor.
	 */
	public GPSModel() {		
		mPositionID = 0;
		try {
			 mParser = new NMEAParser("GPS-Log-III.log");
			 mParser.addListener(this);
		} catch (FileNotFoundException _e1) {
			_e1.printStackTrace();
		}		
	}
	
	/**
	 * Getter for the NMEAParser object.
	 * @return
	 */
	public NMEAParser getParser() {
		return mParser;
	}
	
	/**
	 * Starts the parsing.
	 */
	public void startParsing() {
		// Start parsing
		t = (new Thread(mParser));
		t.start();
	}
	
	/**
	 * Stops the parsing.
	 */
	public void stopParsing() {
		if (t != null)
			mParser.interrupt();
			t.interrupt();	
		}

	/**
	 * Called when the sats are updated. Sets the new position context.
	 */
	@Override
	public void updateSats(NMEAInfo _mInfo) {
		// Set position of poi object to new position (convert coordinates)
		
		Point p = convertLatLong(_mInfo.getLatitude(), _mInfo.getLongitude());	
		PositionContext pos = new PositionContext(mPositionID++, PositionContext.KEY, PositionType.GAUSSKRUEGER, (int) p.getX(), (int) p.getY());
		notifyObservers(pos); // Notify that position has changed
	}
	
	/**
	 * Converts lat & long to world coordinates
	 * @param _lat the latitude.
	 * @param _long the longitude.
	 * @return A point containing the world coordinates.
	 */
	private Point convertLatLong(double _lat, double _long) {
		GPSServer server = new GPSServer();
		return server.convertLatLong(_lat,_long);	
	}
}
