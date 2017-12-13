package at.fhooe.mcm.components.ctxmanagement.simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;

import com.google.gson.Gson;

import at.fhooe.mcm.components.CMComponent;
import at.fhooe.mcm.context.elements.ContextSituation;

/**
 * Simulation Player for the context management component.
 * Handles the playback of a recorded simulation session.
 * @author ifumi
 *
 */
public class CMSimulationPlayer implements Runnable {
	
	private String mPath;
	private CMComponent mComponent;
	private int mInterval;
	
	private boolean mInterrupted = false;
	
	/**
	 * Constructor.
	 * @param _path The path of the stored session.
	 * @param _comp The actual CM component.
	 * @param _interval The interval to use for the playback.
	 */
	public CMSimulationPlayer (String _path, CMComponent _comp, int _interval) {
		mPath = _path;
		mComponent = _comp;
		mInterval = _interval;
	}
	
	/**
	 * Interrupts the playback thread.
	 */
	public void interrupt() {
		mInterrupted = false;
	}

	/**
	 * Sets the interval for the playback.
	 * @param _interval The interval to set.
	 */
	public void setInterval(int _interval) {
		mInterval = _interval;
	}

	/**
	 * The run method handling the actual playback.
	 */
	@Override
	public void run() {
		File folder = new File (mPath + "/");
		
		System.out.println(mPath);
		Gson gson = new Gson();
		
		for (File file : folder.listFiles()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				String content = readStream(fis);
				
				ContextSituation cs = gson.fromJson(content, ContextSituation.class);
				mComponent.update(cs);	
				mComponent.broadcastContextSituation();
				fis.close();
				System.out.println(">> Simulation update!");
				Thread.sleep(mInterval);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
		
		mComponent.stopSimulationPlayback();
	}
	
	/**
	 * Reads a file (inputstream) and returns the content as string.
	 * @param is The input stream
	 * @return The read content as string.
	 */
	public static String readStream(InputStream is) {
	    StringBuilder sb = new StringBuilder(512);
	    try {
	        Reader r = new InputStreamReader(is, "UTF-8");
	        int c = 0;
	        while ((c = r.read()) != -1) {
	            sb.append((char) c);
	        }
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}
	

}
