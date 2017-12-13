package at.fhooe.mcm.components.ctxmanagement;

import at.fhooe.mcm.components.CMComponent;

/**
 * A runnable handling the periodic broadcast of the context situation.
 * @author ifumi
 *
 */
public class CMUpdateThread implements Runnable {

	private CMComponent mComponent;
	
	private long mUpdateInterval = 1000;
	private volatile boolean mInterrupted = false;
		
	/**
	 * Constructor.
	 * @param _cmc The CM Component.
	 */
	public CMUpdateThread(CMComponent _cmc) {
		mComponent = _cmc;
	}

	/**
	 * Sets the interval for the broadcast.
	 * @param _interval The interval to use for the broadcast.
	 */
	public void setInterval(int _interval) {
		mUpdateInterval = _interval;
	}
	
	/**
	 * Interrupts the thread.
	 */
	public void interrupt() {
		mInterrupted = true;
	}

	/**
	 * Resets the thread so its ready to run again after interruption.
	 */
	public void reset() { 
		mInterrupted = false; 
	}

	/**
	 * The run method doing the actual broadcast.
	 */
	@Override
	public void run() {
		while (!mInterrupted) {
			try {
				mComponent.broadcastContextSituation();
				Thread.sleep(mUpdateInterval);
			} catch (InterruptedException _e) {
				// Reserved for future use
			}
		}		
	}
	
}
