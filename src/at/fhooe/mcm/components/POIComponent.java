package at.fhooe.mcm.components;

import java.awt.Color;
import java.awt.Panel;

import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIController;
import at.fhooe.mcm.poi.POIModel;
import at.fhooe.mcm.poi.POIObject;
import at.fhooe.mcm.poi.POIView;

public class POIComponent extends Observable implements IComponent, IObserver {

	public Panel getView() {
		POIModel m = new POIModel();
		POIView v = new POIView(new POIController(m));

		return v.getView();
	}

	public String getName() {
		return "POIComponent";
	}

	@Override
	public void update(Object _o) {
		// TODO Auto-generated method stub
	}
}
