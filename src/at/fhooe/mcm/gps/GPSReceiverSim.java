package at.fhooe.mcm.gps;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Simulator for a GPS receiver.
 * @author Admin
 */
class GPSReceiverSim extends java.io.BufferedReader {
	
	private int mSleepTime;
	private String mFilterString;

	/**
	 * Constructor.
	 * @param _filename Filename/Path of the file to parse.
	 * @param _sleep Sleep time in ms.
	 * @param _filter Filter string.
	 * @throws FileNotFoundException Exception if given file not found.
	 */
	public GPSReceiverSim(String _filename, int _sleep, String _filter) throws FileNotFoundException {
			super(new FileReader(_filename));
			// TODO Auto-generated constructor stub
			mSleepTime = _sleep;
			mFilterString = _filter;
	}

	/**
	 * Reads a line and sleeps for the set sleeptime if the line contains the filter string.
	 */
	public String readLine() {
		String newLine;
		
		try {
			newLine = super.readLine();
		} catch (IOException _e) {
			return null;
		}
		
		if (newLine != null && !newLine.isEmpty() && newLine.contains(mFilterString)) {
			try {
				Thread.sleep(mSleepTime);
				return newLine;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return newLine;
	}
	
	/**
	 * Getter for filter string.
	 * @return Filter string/type.
	 */
	public String getFilterType() {
		return mFilterString;
	}
}
