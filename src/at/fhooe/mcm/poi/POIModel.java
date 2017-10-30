package at.fhooe.mcm.poi;

import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIObject.POI_TYPE;

import static at.fhooe.mcm.gis.DrawingContext.POI_TYPE;

import java.awt.Point;
import java.awt.Polygon;
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
    
    public List<POIObject> getPOIs() {
    	return mPOIs;
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
    
    /**
     * Changes visibility of all POIs with the specific type.
     * @param _visible Visibility of type
     * @param _type Type of POI to change visibility for
     */
    public void setVisibleByType(boolean _visible, POI_TYPE _type) {
    	for (POIObject poi : mPOIs) {
    		if (poi.getPOIType() == _type)
    			poi.setVisible(_visible);
    	}
    }
}
