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
    
    public void updateContextSituation(ContextSituation _cs) {
    	mModel.setContextSituation(_cs);
    }
    
	public void recordContextSituation(ContextSituation _cs) {
		if (isRecording())
			mRecorder.writeContextSituation(_cs);
	}
    
    public boolean isRecording() {
    	return mIsRecording;
    }
    
    public void startSimulationRecording() {
        if (!mIsRecording) {
            mRecorder = new CMSimulationRecorder();
            mIsRecording = true;
            System.out.println(">> Simulation recording started!");
        }
    }
    
    public void stopSimulationRecording() {
        if (mIsRecording) {
            mRecorder = null;
            mIsRecording = false;
            System.out.println(">> Simulation recording stopped!");
        }
    }
    
    public void togglePeriodicUpdate() {
    	if (mThreadRunning)
    		stopPeriodicUpdate();
    	else
    		startPeriodicUpdate();
    }
       
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
    
    private void stopPeriodicUpdate() {
    	if (mUpdateThread != null && mThreadRunning) {
    	    mView.getToggleButton().setBackground(Color.white);
    		mCMUpdateThread.interrupt();
    		mUpdateThread.interrupt();
            mThreadRunning = false;
    		System.out.println(">> Periodic Context Situation Update stopped!");
    	}
    }
    
    public void broadcastContextSituation() {
    	ContextSituation cs = mModel.getContextSituation();
    	mMediator.notifyObservers(cs);
    	mMediator.update(cs);
    }

    @Override
    public Panel getView() {
        return mPanel;
    }

    @Override
    public String getName() {
        return "Context Management";
    }

    @Override
    public void init(Mediator _mediator) {
        mMediator = _mediator;
    }

    @Override
    public void setUI(IUIView _view) {

    }

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

    public CMUpdateThread getCMUpdateThread() {
        return mCMUpdateThread;
    }

	public void startSimulationPlayback(String _path) {
        if (!mIsPlaying) {
            mPlayer = new CMSimulationPlayer(_path, this, mView.getSlider().getValue());

            Thread t  = new Thread(mPlayer);
            t.start();

            mIsPlaying = true;
        }
	}
	
	public void stopSimulationPlayback() {
		if (mIsPlaying) {
			mPlayer.interrupt();
			mIsPlaying = false;
		}
	}

	public boolean isPlaying() {
		return mIsPlaying;
	}

	public CMSimulationPlayer getSimulationPlayer() {
		return mPlayer;
	}


}
