package at.fhooe.mcm.components;

import java.awt.Color;
import java.awt.Panel;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gis.GISModel;
import at.fhooe.mcm.components.poi.POIController;
import at.fhooe.mcm.components.poi.POIModel;
import at.fhooe.mcm.components.poi.POIView;
import at.fhooe.mcm.components.poi.POI_TYPE;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;

/**
 * POI Component handling the selection of POIs to display
 * @author ifumi
 *
 */
public class POIComponent extends Observable implements IComponent, IObserver {

	private Panel view;
	IMediator mMediator;
	POIModel mModel;

	/**
	 * Constructor.
	 */
	public POIComponent(){
		mModel = new POIModel();
		POIView v = new POIView(new POIController(mModel));
		view = v.getView();

		mModel.addObserver(this, ObserverType.GIS);
	}
	
	/**
	 * Getter for the POI View.
	 */
	public Panel getView() {
		return view;
	}

	/**
	 * Getter for the components name.
	 */
	public String getName() {
		return "POIComponent";
	}
	
	/**
	 * Sets all POIs with the passed type visible.
	 * @param _type The type to set visible.
	 */
	public void showPOIType(POI_TYPE _type) {
		mModel.setVisibleByType(true, _type);
	}
	
	/**
	 * Sets all POIs with the passed type invisible.
	 * @param _type The type to set invisible.
	 */
	public void hidePOIType(POI_TYPE _type) {
		mModel.setVisibleByType(false, _type);
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
	 * Update method passing objects straight through to the mediator.
	 */
	@Override
	public void update(Object _o) {
		mMediator.update(_o);
	}
}
