package at.fhooe.mcm.gps;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Parser for files containing NMEA data.
 * @author Admin
 */
public class NMEAParser implements Runnable {

	private GPSReceiverSim mGPSReceiver;
	private NMEAInfo mInfo;
	private NMEAInfo mInfoLoad;
	private ArrayList<PositionUpdateListener> listener;
	
	/**
	 * Constructor.
	 * @throws FileNotFoundException Thrown if filename not fount.
	 */
	public NMEAParser(String _filename) throws FileNotFoundException {
		mGPSReceiver = new GPSReceiverSim(_filename, 1000, "$GPGGA");
		mInfo = new NMEAInfo();
		mInfoLoad = new NMEAInfo();
		listener = new ArrayList<PositionUpdateListener>();
	}

	/**
	 * Updates all listeners.
	 */
	public void update() {
		mInfo = mInfoLoad;
		mInfoLoad = new NMEAInfo();
		for (PositionUpdateListener l : listener)
			l.updateSats(mInfo);
	}
	
	/**
	 * Adds a listener.
	 * @param _listener Given listener.
	 */
	public void addListener(PositionUpdateListener _listener) {
		listener.add(_listener);
	}
	
	/**
	 * Thread - run method.
	 */
	@Override
	public void run() {
		String line;
		ArrayList<Integer> usedSats = new ArrayList<Integer>();

		do {		
			line = mGPSReceiver.readLine();
			
			if (line != null && !line.isEmpty()) {
				if (line.contains("*"))
					line = line.substring(0, line.indexOf("*"));
				String[] sp = line.split(",");
				switch (sp[0]) {
				case "$GPGGA": 
					
					// Check if ready for update -> Update listeners
					if (mInfoLoad.isReadyForUpdate()) {
						update();
					}		
					
					String latitude = sp[2];
					String longitude = sp[4];
					
					float latDeg = 0;
					float longDeg = 0;
					
					// Get degrees
					if (!latitude.isEmpty())	
						latDeg = Integer.parseInt(latitude.substring(0, 2)) + // Degrees
									   Float.parseFloat(latitude.substring(2, latitude.length() - 1))/60; // Minutes
					if (!longitude.isEmpty())
						longDeg = Integer.parseInt(longitude.substring(0, 3)) + // Degrees
								   		Float.parseFloat(longitude.substring(3, longitude.length() - 1))/60; // Minutes
					
					
					if (sp[3] == "S") {
						latDeg *= -1;
					}
					if (sp[3] == "W") {
						longDeg *= -1;
					}
					
					
					mInfoLoad.setLatitude(latDeg);
					mInfoLoad.setLongitude(longDeg);
					// Set Time
					if (sp.length > 1 && !sp[1].isEmpty())
						mInfoLoad.setTime(Float.parseFloat(sp[1]));
					// Set fix-quality
					if (sp.length > 6 && !sp[6].isEmpty())
						mInfoLoad.setFixQuality(Integer.parseInt(sp[6]));
					// Set satellite count
					if (sp.length > 7 && !sp[7].isEmpty())
						mInfoLoad.setSatCount(Integer.parseInt(sp[7]));
					// Set HDOP
					if (sp.length > 8 && !sp[8].isEmpty())
						mInfoLoad.setHDOP(Float.parseFloat(sp[8]));
					// Set height above genoid
					if (sp.length > 9 && !sp[9].isEmpty())
						mInfoLoad.setHeight(Float.parseFloat(sp[9]));
				
					
					System.out.println("Latitude: " + latDeg + sp[3] +", Longitude: " + longDeg + sp[5]);
					break;
				case "$GPGSA": 
					
					usedSats.clear();
					try {
						// Get used satellites
						for (int i = 3; i < sp.length; i++) {
							if (!sp[i].isEmpty())
								usedSats.add(Integer.parseInt(sp[i]));
							else 
								break;
						}		
						
						// Set PDOP
						if (sp.length > 15 && !sp[15].isEmpty())
							mInfoLoad.setPDOP(Float.parseFloat(sp[15]));
						// Set HDOP
						if (sp.length > 16 && !sp[16].isEmpty())
							mInfoLoad.setHDOP(Float.parseFloat(sp[16]));
						// Set VDOP
						if (sp.length > 17 && !sp[17].isEmpty())
							mInfoLoad.setVDOP(Float.parseFloat(sp[17]));
					} catch (Exception _e) {
						
					}

					break; 
				case "$GPGSV": 
					
					for (int i = 0; (i + 7) < sp.length; i = i + 4) {
						
						int noSat = 0;
						int hoAng = 0;
						int vertAng = 0;
						int SNR = 0;
						
						
						// Get number of satellite
						if (!sp[4 + i].isEmpty())
							noSat = Integer.parseInt(sp[4 + i]);
						// Get vertical angle
						if (!sp[5 + i].isEmpty()) 
							vertAng = Integer.parseInt(sp[5 + i]);
						// Get horizontal angle
						if (!sp[6 + i].isEmpty())
							hoAng = Integer.parseInt(sp[6 + i]);
						// Get SNR
						if (!sp[7 + i].isEmpty())
							SNR = Integer.parseInt(sp[7 + i]);
						
						mInfoLoad.addSatInfo(new SatelliteInfo(noSat, hoAng, vertAng, SNR, usedSats.contains(noSat)));

					}
					break;
					
					default:				
				}	
			}
		} while (line != null && !Thread.currentThread().isInterrupted());
	}
}
