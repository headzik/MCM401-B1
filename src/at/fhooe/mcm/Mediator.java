package at.fhooe.mcm;

import at.fhooe.mcm.components.*;
import at.fhooe.mcm.components.poi.POIObject;
import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.parsers.DomParser;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.objects.Observable.ObserverType;
import at.fhooe.mcm.views.MediatorView;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.List;

public class Mediator extends Observable implements IMediator, IObserver {

    private MediatorView mMediatorView;
    private List<IComponent> mComponents;

    public Mediator() {
        mMediatorView = new MediatorView();
        mComponents = new ArrayList<>();

        GISComponent g = new GISComponent();
        mComponents.add(g);
        addObserver(g, ObserverType.GIS);

        GPSComponent gps = new GPSComponent(this);
        mComponents.add(gps);

        POIComponent p = new POIComponent(this);
        mComponents.add(p);

        AALComponent a = new AALComponent(this);
        mComponents.add(a);

        CMComponent c = new CMComponent(this);
        mComponents.add(c);
        addObserver(c, ObserverType.CM);

        mMediatorView.addTabs(mComponents);

    }

    public static void main(String[] args) {
        Mediator m = new Mediator();
    }

    @Override
    public void update(Object _o) {

        if (_o instanceof ContextElement) {
            notifyObservers(_o, ObserverType.CM);        
        } else if (_o instanceof ContextSituation) {
        	// Notify all ObserverTypes which need ContextSituation here!
        	// For now we only need position updates.
        	notifyObservers(_o, ObserverType.GIS);
        } else if (_o instanceof POIObject) {
            notifyObservers(_o, ObserverType.GIS);
        } 
        
    }
}
