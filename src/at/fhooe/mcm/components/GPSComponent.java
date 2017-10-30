package at.fhooe.mcm.components;

import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

public class GPSComponent extends Observable implements IComponent, IObserver {
    @Override
    public Panel getView() {
		Panel p = new Panel();
		p.setBackground(Color.BLUE);

		return p;
    }

    @Override
    public String getName() {
        return "GPSComponent";
    }

	@Override
	public void update(Object _o) {
		// TODO Auto-generated method stub
        System.out.println("GPSComponent update");
	}
}
