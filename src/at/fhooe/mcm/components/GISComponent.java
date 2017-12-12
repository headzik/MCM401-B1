package at.fhooe.mcm.components;

import java.awt.Panel;
import java.util.ArrayList;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.components.gis.GISModel;
import at.fhooe.mcm.components.gis.GISView;
import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.components.gis.warnings.IWarningType;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IDrawingContext;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;


public class GISComponent extends Observable implements IComponent, IObserver{

	private GISView mView;
	private GISController mController;
	private GISModel mModel;
	private Mediator mMediator;
	private ArrayList<IWarningType> mWarnings;

	public GISComponent() {
		mModel = new GISModel();
		mController = new GISController(mModel);
		GISView v = new GISView(mController);

		mController.setView(v);

		mModel.addObserver(v);
		mView = v;
		
		mWarnings = new ArrayList<>();
	}
	
	public void setWarning(IWarningType warning) {
		mWarnings.add(warning);
		//display warning
		System.out.println("Received warning of type: " + warning.toString());
	}
	
	@Override
	public Panel getView() {
		return mView.getView();
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
		_view.setController(mController);
		mView.setPanel(_view.getView());
	}
	public void setDrawingContext(IDrawingContext _drawingContext){
		mModel.setDrawingContext(_drawingContext);
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
