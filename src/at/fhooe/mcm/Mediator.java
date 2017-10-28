package at.fhooe.mcm;

import at.fhooe.mcm.components.GISComponent;
import at.fhooe.mcm.components.GPSComponent;
import at.fhooe.mcm.components.POIComponent;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.views.MediatorView;

import java.util.ArrayList;
import java.util.List;

public class Mediator implements IMediator {

    private MediatorView mMediatorView;
    private List<IComponent> mComponents;

    public Mediator() {
        mMediatorView = new MediatorView();
        mComponents = new ArrayList<>();

        GISComponent g = new GISComponent();
        mComponents.add(g);        

        GPSComponent gps = new GPSComponent();
        mComponents.add(gps);

        POIComponent p = new POIComponent();
        mComponents.add(p);

        mMediatorView.addTabs(mComponents);
    }

    public static void main(String[] args) {
        Mediator m = new Mediator();


    }
}
