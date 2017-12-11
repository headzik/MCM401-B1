package at.fhooe.mcm;

import at.fhooe.mcm.components.*;
import at.fhooe.mcm.components.poi.POIObject;
import at.fhooe.mcm.components.reflection.ComponentsFactory;
import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.views.MediatorView;

import java.util.List;

public class Mediator extends Observable implements IMediator, IObserver {

    private MediatorView mMediatorView;
    private List<IComponent> mComponents;

    public Mediator() {
        mMediatorView = new MediatorView();
        mComponents = new ComponentsFactory().buildComponents("src/at/fhooe/mcm/components/reflection/ComponentComposition.xml", this);

        for (IComponent c : mComponents){
            if (c instanceof GISComponent){
                addObserver((GISComponent) c, ObserverType.GIS);
            }
            if (c instanceof CMComponent){
                addObserver((CMComponent) c, ObserverType.CM);
            }
        }

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
