package at.fhooe.mcm.poi;

import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.util.ArrayList;
import java.util.List;

public class POIModel extends Observable {

    private List<POIObject> mPOIs;

    public POIModel(){
        mPOIs = new ArrayList<>();
    }

    public void addPOI(POIObject _poi){
        mPOIs.add(_poi);
    }

    public void removePOI(POIObject _poi) {
        mPOIs.clear();
    }

    public void update() {
        for (POIObject poi : mPOIs){
            notifyObservers(poi);
        }
    }

    @Override
    public void addObserver(IObserver _observer) {
        super.addObserver(_observer);
    }
}
