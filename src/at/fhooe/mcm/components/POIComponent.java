package at.fhooe.mcm.components;

import java.awt.Color;
import java.awt.Panel;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.gis.GISModel;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIController;
import at.fhooe.mcm.poi.POIModel;
import at.fhooe.mcm.poi.POIView;

public class POIComponent extends Observable implements IComponent, IObserver {

	private Panel view;
	IMediator mediator;
	POIModel mModel;

	public POIComponent(Mediator _mediator){
		mModel = new POIModel();
		POIView v = new POIView(new POIController(mModel));
		view = v.getView();

		mediator = _mediator;

		mModel.addObserver(this, ObserverType.GIS);
	}
	
	public Panel getView() {
		return view;
	}

	public String getName() {
		return "POIComponent";
	}

	@Override
	public void update(Object _o) {
		mediator.update(_o);
	}
}
