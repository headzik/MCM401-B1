package at.fhooe.mcm;

import at.fhooe.mcm.components.GISComponent;
import at.fhooe.mcm.components.GPSComponent;
import at.fhooe.mcm.components.POIComponent;
import at.fhooe.mcm.gis.GeoObject;
import at.fhooe.mcm.gis.OSMServer;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.views.MediatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Mediator extends Observable implements IMediator, IObserver {

    private MediatorView mMediatorView;
    private List<IComponent> mComponents;

    public Mediator() {
        mMediatorView = new MediatorView();
        mComponents = new ArrayList<>();

        GISComponent g = new GISComponent();
        mComponents.add(g);
        addObserver(g);

        POIComponent p = new POIComponent(this);
        mComponents.add(p);

        GPSComponent gps = new GPSComponent();
        mComponents.add(gps);


        mMediatorView.addTabs(mComponents);

    }

    public static void main(String[] args) {
        Mediator m = new Mediator();
        m.loadData();
    }

    private void loadData() {
        OSMServer server = new OSMServer();
        for (GeoObject ob : server.extractData()){
            notifyObservers(ob);
        }
    }

    @Override
    public void update(Object _o) {
        notifyObservers(_o);
    }
}
