package at.fhooe.mcm.components;

import at.fhooe.mcm.gps.GPSController;
import at.fhooe.mcm.gps.GPSModel;
import at.fhooe.mcm.gps.GPSView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

public class GPSComponent extends Observable implements IComponent, IObserver {

    private Panel view;

    public GPSComponent() {
        GPSModel m = new GPSModel();
        GPSController c = new GPSController(m);
        GPSView v = new GPSView(c);

        view = v.getView();

    }

    @Override
    public Panel getView() {
        return view;
    }

    @Override
    public String getName() {
        return "GPSComponent";
    }

    @Override
    public void update(Object _o) {
        // TODO Auto-generated method stub
        // System.out.println("GPSComponent update");
    }
}
