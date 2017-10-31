package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.gps.DataView;
import at.fhooe.mcm.gps.GPSController;
import at.fhooe.mcm.gps.GPSModel;
import at.fhooe.mcm.gps.GPSView;
import at.fhooe.mcm.gps.NMEAParser;
import at.fhooe.mcm.gps.SatView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;
import java.io.FileNotFoundException;

public class GPSComponent extends Observable implements IComponent, IObserver {
	
	private Panel view;
	private GPSModel mModel;
    private GPSController mController;
    private GPSView mView;

    private Mediator mMediator;
	
	public GPSComponent(Mediator _mediator) {
		mModel = new GPSModel();
		mController = new GPSController(mModel);
		mView = new GPSView(mController, mModel.getParser());
		
		view = mView.getView();
		mMediator = _mediator;
		mModel.addObserver(this);
	}
	
	@Override
    public Panel getView() {
		return view;
    }

    @Override
    public String getName() {
        return "GPSComponent";
    }

	@Override
	public void update(Object _o) {
		mMediator.update(_o);
	}
}
