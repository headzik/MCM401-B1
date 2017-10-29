package at.fhooe.mcm.components;

import at.fhooe.mcm.gis.GISController;
import at.fhooe.mcm.gis.GISModel;
import at.fhooe.mcm.gis.GISView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;

import java.awt.*;


public class GISComponent implements IComponent, IObserver{

	private Panel view;
	GISModel mModel;

	public GISComponent() {
		mModel = new GISModel();
		GISController c = new GISController(mModel);
		GISView v = new GISView(c);

		mModel.addObserver(v);

		view = v.getView();
	}
	
	@Override
	public Panel getView() {
		return view;
	}

	@Override
	public String getName() {
		return "GISComponent";
	}

	@Override
	public void update(Object _o) {
		mModel.addObject(_o);
	}

}
