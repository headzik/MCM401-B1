package at.fhooe.mcm.components;

import java.awt.Panel;
import java.io.IOException;
import java.util.ArrayList;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.components.gis.GISModel;
import at.fhooe.mcm.components.gis.GISView;
import at.fhooe.mcm.components.gis.GeoObject;
import at.fhooe.mcm.components.gis.warnings.WarningType;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IDrawingContext;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;

/**
 * The GIS Component handling the map and display of all contextinformation.
 * @author ifumi
 *
 */
public class GISComponent extends Observable implements IComponent, IObserver{

	private GISView mView;
	private GISController mController;
	private GISModel mModel;
	private Mediator mMediator;
	private ArrayList<WarningType> mWarnings;

	/**
	 * Constructor.
	 */
	public GISComponent() {
		mModel = new GISModel();
		mController = new GISController(mModel);
		GISView v = new GISView(mController);

		mController.setView(v);

		mModel.addObserver(v);
		mView = v;
		
		mWarnings = new ArrayList<>();
	}
	
	/**
	 * Sets a warning.
	 * @param warning The type of the warning to set.
	 */
	public void setWarning(WarningType warning) {
		if (!mWarnings.contains(warning))
			mWarnings.add(warning);
		//display warning
		System.out.println("Received warning of type: " + warning.toString());
		mModel.updateImg();
	}
	
	/**
	 * Getter for the GIS View.
	 */
	@Override
	public Panel getView() {
		return mView.getView();
	}

	/**
	 * Getter for the components name
	 */
	@Override
	public String getName() {
		return "GISComponent";
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
		_view.setController(mController);
		mView.setPanel(_view.getView());
	}

	/**
	 * Setter for the drawing context.
	 * @param _drawingContext The drawing context to use.
	 */
	public void setDrawingContext(IDrawingContext _drawingContext){
		mModel.setDrawingContext(_drawingContext);
		mModel.updateImg();
	}

	/**
	 * Update method.
	 */
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

	/**
	 * Updates the view with the current set warnings.
	 */
	public void updateWarnings() {
		try {
			mView.updateWarnings(mWarnings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears all warnings from the list.
	 */
	public void clearWarnings() {
		mWarnings.clear();
	}
}
