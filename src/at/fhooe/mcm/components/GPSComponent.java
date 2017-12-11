package at.fhooe.mcm.components;


import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gps.GPSController;
import at.fhooe.mcm.components.gps.GPSModel;
import at.fhooe.mcm.components.gps.GPSView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

public class GPSComponent extends Observable implements IComponent, IObserver {
	
	private Panel view;
	private GPSModel mModel;
    private GPSController mController;
    private GPSView mView;

    private Mediator mMediator;
	
	public GPSComponent() {
		mModel = new GPSModel();
		mController = new GPSController(mModel);
		mView = new GPSView(mController, mModel.getParser());
		
		view = mView.getView();
		mModel.addObserver(this, ObserverType.GIS);
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
	public void init(Mediator _mediator) {
		mMediator = _mediator;
	}

	@Override
	public void update(Object _o) {
		mMediator.update(_o);
	}
}
