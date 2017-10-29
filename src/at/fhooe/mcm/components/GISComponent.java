package at.fhooe.mcm.components;

import at.fhooe.mcm.GIS.GISModel;
import at.fhooe.mcm.GIS.GISView;
import at.fhooe.mcm.GIS.GISController;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;

import java.awt.*;


public class GISComponent implements IComponent, IObserver{

	private Panel view;

	public GISComponent() {
		GISModel m = new GISModel();   
		GISController c = new GISController(m);   
		GISView v = new GISView(c);
		
		m.addMapObserver(v);
		
		view = v.getPanelView();
	}
	
	@Override
	public Panel getView() {
		return view;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "GISComponent";
	}

	@Override
	public void update(Object _o) {
		// TODO Auto-generated method stub
		
	}

}
