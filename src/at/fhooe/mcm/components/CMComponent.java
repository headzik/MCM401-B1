package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.ctxmanagement.CMController;
import at.fhooe.mcm.components.ctxmanagement.CMModel;
import at.fhooe.mcm.components.ctxmanagement.CMUpdateThread;
import at.fhooe.mcm.components.ctxmanagement.CMView;
import at.fhooe.mcm.components.ctxmanagement.simulation.CMSimulationPlayer;
import at.fhooe.mcm.components.ctxmanagement.simulation.CMSimulationRecorder;
import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

/**
 * Context Management component. Handles all manipulation and simulation of the ContextSituation.
 * @author ifumi
 *
 */
public class CMComponent implements IComponent, IObserver {

    private Panel mPanel;
    private Mediator mMediator;
    private CMModel mModel;
    private CMView mView;
    
    private Thread mUpdateThread;
    private CMUpdateThread mCMUpdateThread;
    private boolean mThreadRunning = false;
    
    private CMSimulationRecorder mRecorder;
    private CMSimulationPlayer mPlayer;
    private boolean mIsRecording, mIsPlaying;

    /**
     * Constructor.
     */
    public CMComponent(){
        mModel = new CMModel();
        CMController controller = new CMController(mModel, this);
        CMView view = new CMView(controller);
        mView = view;
        controller.setView(view);
        mPanel = view.getView();
        mModel.addObserver(this, Observable.ObserverType.CM);
        mCMUpdateThread = new CMUpdateThread(this);
    }
    
    /**
     * Called whenever the context situation changes.
     * @param _cs The ContextSituation.
     */
    public void updateContextSituation(ContextSituation _cs) {
    	mModel.setContextSituation(_cs);
    }
    
    /**
     * Writes the passed context situation to a file for later simulation uses.
     * @param _cs The ContextSituation to write.
     */
	public void recordContextSituation(ContextSituation _cs) {
		if (isRecording())
			mRecorder.writeContextSituation(_cs);
	}
    
	/**
	 * True if a simulation is being recorded.
	 * @return
	 */
    public boolean isRecording() {
    	return mIsRecording;
    }
    
    /**
     * Starts the recording of a simulation session.
     */
    public void startSimulationRecording() {
        if (!mIsRecording) {
            mRecorder = new CMSimulationRecorder();
            mIsRecording = true;
            System.out.println(">> Simulation recording started!");
        }
    }
    
    /**
     * Stops the recording of a simulation session.
     */
    public void stopSimulationRecording() {
        if (mIsRecording) {
            mRecorder = null;
            mIsRecording = false;
            System.out.println(">> Simulation recording stopped!");
        }
    }
    
    /**
     * Toggles the periodic ContextSimulation broadcast.
     */
    public void togglePeriodicUpdate() {
    	if (mThreadRunning)
    		stopPeriodicUpdate();
    	else
    		startPeriodicUpdate();
    }
       
    /**
     * Starts the periodic ContextSimulation broadcast.
     */
    private void startPeriodicUpdate() {
        if (!mThreadRunning) {
            mView.getToggleButton().setBackground(Color.GREEN);
            mCMUpdateThread.reset();
            mUpdateThread = new Thread(mCMUpdateThread);
            mUpdateThread.start();
            mThreadRunning = true;
            System.out.println(">> Periodic Context Situation Update started!");
        }
    }
    
	/**
	 * Stops the periodic ContextSimulation broadcast.
	 */
    private void stopPeriodicUpdate() {
    	if (mUpdateThread != null && mThreadRunning) {
    	    mView.getToggleButton().setBackground(Color.white);
    		mCMUpdateThread.interrupt();
    		mUpdateThread.interrupt();
            mThreadRunning = false;
    		System.out.println(">> Periodic Context Situation Update stopped!");
    	}
    }
    
    /**
     * Broadcasts the current ContextSituation
     */
    public void broadcastContextSituation() {
    	ContextSituation cs = mModel.getContextSituation();
    	mMediator.notifyObservers(cs);
    	mMediator.update(cs);
    }

    /**
     * Getter for the CM View.
     */
    @Override
    public Panel getView() {
        return mPanel;
    }

    /** Getter for the components name.
     * 
     */
    @Override
    public String getName() {
        return "Context Management";
    }

    /**
     * Init method setting the mediator.
     */
    @Override
    public void init(Mediator _mediator) {
        mMediator = _mediator;
    }

    /**
     * Setter for the UI View.
     */
    @Override
    public void setUI(IUIView _view) {

    }

    /**
     * Update method which updates all gui elements and saves the received context situation (from mediator).
     */
    @Override
    public void update(Object _o) {
        if (_o instanceof ContextElement){
            mModel.setContextElement((ContextElement)_o);
            mView.updateGUIElements(mModel.getContextSituation());    
        }
        
        if (_o instanceof ContextSituation) {
        	mModel.setContextSituation((ContextSituation) _o); 
        	mView.updateGUIElements(mModel.getContextSituation());
        }
    }

    /**
     * Getter for the CM Update Thread (runnable)
     * @return The runnable.
     */
    public CMUpdateThread getCMUpdateThread() {
        return mCMUpdateThread;
    }

    /**
     * Starts the playback of recorded simulation session.
     * @param _path Path of the stored simulation session to play.
     */
	public void startSimulationPlayback(String _path) {
        if (!mIsPlaying) {
            mPlayer = new CMSimulationPlayer(_path, this, mView.getSlider().getValue());

            Thread t  = new Thread(mPlayer);
            t.start();

            mIsPlaying = true;
        }
	}
	
	/**
	 * Stops the playback of a recorded simulation session.
	 */
	public void stopSimulationPlayback() {
		if (mIsPlaying) {
			mPlayer.interrupt();
			mIsPlaying = false;
		}
	}

	/**
	 * Returns true if a simulation session is currently played.
	 * @return True if playing, false otherwise.
	 */
	public boolean isPlaying() {
		return mIsPlaying;
	}

	/**
	 * Returns the CM Simulation Player object which handles all the playing.
	 * @return
	 */
	public CMSimulationPlayer getSimulationPlayer() {
		return mPlayer;
	}
}
