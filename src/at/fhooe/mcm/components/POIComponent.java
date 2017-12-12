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

public class POIComponent extends Observable implements IComponent, IObserver {

	private Panel view;
	IMediator mMediator;
	POIModel mModel;

	public POIComponent(){
		mModel = new POIModel();
		POIView v = new POIView(new POIController(mModel));
		view = v.getView();

		mModel.addObserver(this, ObserverType.GIS);
	}
	
	public Panel getView() {
		return view;
	}

	public String getName() {
		return "POIComponent";
	}

	public void showPOIType(POI_TYPE _type) {
		mModel.setVisibleByType(true, _type);
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
		mMediator.update(_o);
	}
}
