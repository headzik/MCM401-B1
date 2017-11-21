package at.fhooe.mcm.poi;

import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;
import at.fhooe.mcm.poi.POIObject.POI_TYPE;

import java.util.List;

public class POIModel extends Observable {

    private List<POIObject> mPOIs;

    public POIModel(){
        POIServer poi = new POIServer();
        mPOIs = poi.extractData();
    }

    public void update() {
        for (POIObject poi : mPOIs){
            notifyObservers(poi);
        }
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
