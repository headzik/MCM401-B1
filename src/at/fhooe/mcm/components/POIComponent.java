package at.fhooe.mcm.components;

import java.awt.Color;
import java.awt.Panel;

import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;

public class POIComponent implements IComponent, IObserver {

	public Panel getView() {
		Panel p = new Panel();
		p.setBackground(Color.RED);
		return p;
	}
	
	public String getName() {
		return "POIComponent";
	}

	@Override
	public void update(Object _o) {
		// TODO Auto-generated method stub
	}
}
