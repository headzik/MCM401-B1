package at.fhooe.mcm.components;

import java.awt.Panel;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.components.gis.GISModel;
import at.fhooe.mcm.components.gis.GISView;
import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;


public class GISComponent extends Observable implements IComponent, IObserver{

	private Panel mView;
	private GISModel mModel;
	private Mediator mMediator;

	public GISComponent() {
		mModel = new GISModel();
		GISController c = new GISController(mModel);
		GISView v = new GISView(c);

		c.setView(v);

		mModel.addObserver(v);
		mView = v.getView();
	}
	
	@Override
	public Panel getView() {
		return mView;
	}

	@Override
	public String getName() {
		return "GISComponent";
	}

	@Override
	public void init(Mediator _mediator) {
		mMediator = _mediator;
	}

	@Override
	public void setUI(IUIView _view) {
		mView = _view.getPanel();
	}

	@Override
	public void update(Object _o) {
		if (_o instanceof GeoObject && !mModel.containsObject(_o))
			mModel.addObject(_o);
		
		// Position Update?
		if (_o instanceof ContextSituation) {
			if (((ContextSituation) _o).getPositionContext() != null) {			
				PositionContext pc = ((ContextSituation) _o).getPositionContext();
				mModel.positionUpdate(pc);
				mModel.drawPolygons();
			}
		}

	}
}
