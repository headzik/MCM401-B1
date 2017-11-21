package at.fhooe.mcm.components.ctxmanagement;

import at.fhooe.mcm.components.CMComponent;

public class CMUpdateThread implements Runnable {

	private CMComponent mComponent;
	
	private long mUpdateInterval = 1000;
	private volatile boolean mInterrupted = false;
		
	public CMUpdateThread(CMComponent _cmc) {
		mComponent = _cmc;
	}

	public void setInterval(int _interval) {
		mUpdateInterval = _interval;
	}
	
	public void interrupt() {
		mInterrupted = true;
	}

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
