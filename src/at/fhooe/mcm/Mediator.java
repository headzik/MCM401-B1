package at.fhooe.mcm;

import at.fhooe.mcm.components.*;
import at.fhooe.mcm.contextparsers.DomParser;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IMediator;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.views.MediatorView;

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
        addObserver(g);

        GPSComponent gps = new GPSComponent(this);
        mComponents.add(gps);

        POIComponent p = new POIComponent(this);
        mComponents.add(p);

        AALComponent a = new AALComponent(AALComponent.ParseMode.DOM);
        mComponents.add(a);

        CMComponent c = new CMComponent(this);
        mComponents.add(c);

        mMediatorView.addTabs(mComponents);

    }

    public static void main(String[] args) {
        Mediator m = new Mediator();
    }

    @Override
    public void update(Object _o) {
        notifyObservers(_o);
    }
}
