package at.fhooe.mcm.components;

import java.awt.Color;
import java.awt.Panel;

import at.fhooe.mcm.interfaces.IComponent;

public class POIComponent implements IComponent {

	public Panel getView() {
		Panel p = new Panel();
		p.setBackground(Color.RED);
		return p;
	}
	
	public String getName() {
		return "POIComponent";
	}
}
