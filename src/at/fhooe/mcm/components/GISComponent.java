package at.fhooe.mcm.components;

import at.fhooe.mcm.GIS.GISModel;
import at.fhooe.mcm.GIS.GISView;
import at.fhooe.mcm.GIS.GISController;
import at.fhooe.mcm.interfaces.IComponent;

import java.awt.*;


public class GISComponent implements IComponent {

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
        return "GISComponent";
    }
}
