package at.fhooe.mcm.components;

import at.fhooe.mcm.gis.GISModel;
import at.fhooe.mcm.gps.GPSController;
import at.fhooe.mcm.gps.GPSModel;
import at.fhooe.mcm.gps.GPSView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;
import java.io.FileNotFoundException;

public class GPSComponent extends Observable implements IComponent, IObserver {

    private Panel view;
	GPSModel mModel;

    public GPSComponent() {
		mModel = new GPSModel();
        GPSController c = new GPSController(mModel);
        GPSView v = new GPSView(c);

        mModel.addListener(v);
        
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
