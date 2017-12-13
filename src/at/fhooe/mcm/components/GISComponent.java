package at.fhooe.mcm.components;

import java.awt.Panel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.components.gis.GISModel;
import at.fhooe.mcm.components.gis.GISView;
import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.components.gis.warnings.WarningType;
import at.fhooe.mcm.components.poi.POIObject;
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
	private ArrayList<WarningType> mWarnings;

	public GISComponent() {
		mModel = new GISModel();
		mController = new GISController(mModel);
		GISView v = new GISView(mController);

		mController.setView(v);

		mModel.addObserver(v);
		mView = v;
		
		mWarnings = new ArrayList<>();
	}
	
	public void setWarning(WarningType warning) {
		if (!mWarnings.contains(warning))
			mWarnings.add(warning);
		//display warning
		System.out.println("Received warning of type: " + warning.toString());
		mModel.updateImg();
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
		mModel.updateImg();
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
		clearWarnings();

		if (_o == null)
			mModel.updateImg();
	}

	public void updateWarnings() {
		try {
			mView.updateWarnings(mWarnings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearWarnings() {
		mWarnings.clear();
	}
}
