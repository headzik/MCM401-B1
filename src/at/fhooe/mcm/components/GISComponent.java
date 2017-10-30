package at.fhooe.mcm.components;

import at.fhooe.mcm.gis.GISController;
import at.fhooe.mcm.gis.GISModel;
import at.fhooe.mcm.gis.GISView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;


public class GISComponent extends Observable implements IComponent, IObserver{

	private Panel view;
	GISModel mModel;

	public GISComponent() {
		mModel = new GISModel();
		GISController c = new GISController(mModel);
		GISView v = new GISView(c);

		c.setView(v);

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
