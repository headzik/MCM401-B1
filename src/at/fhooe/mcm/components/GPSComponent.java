package at.fhooe.mcm.components;


import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gps.GPSController;
import at.fhooe.mcm.components.gps.GPSModel;
import at.fhooe.mcm.components.gps.GPSView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

/**
 * GPS Component. Handling the simulation of the users position.
 * @author ifumi
 *
 */
public class GPSComponent extends Observable implements IComponent, IObserver {
	
	private Panel view;
	private GPSModel mModel;
    private GPSController mController;
    private GPSView mView;

    private Mediator mMediator;
	
    /**
     * Constructor.
     */
	public GPSComponent() {
		mModel = new GPSModel();
		mController = new GPSController(mModel);
		mView = new GPSView(mController, mModel.getParser());
		
		view = mView.getView();
		mModel.addObserver(this, ObserverType.GIS);
	}
	
	/**
	 * Getter for the GPS View.
	 */
	@Override
    public Panel getView() {
		return view;
    }

	/**
	 * Getter for the components name.
	 */
    @Override
    public String getName() {
        return "GPSComponent";
    }

    /**
     * Init method setting the mediator.
     */
	@Override
	public void init(Mediator _mediator) {
		mMediator = _mediator;
	}

	/**
	 * Setter for the UI View
	 */
	@Override
	public void setUI(IUIView _view) {

	}

	/**
	 * Update method passing the received objects straight through to the mediator.
	 */
	@Override
	public void update(Object _o) {
		mMediator.update(_o);
	}
}
