package at.fhooe.mcm.components.poi;

import at.fhooe.mcm.components.poi.POIObject.POI_TYPE;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.util.List;

public class POIModel extends Observable {

    private List<POIObject> mPOIs;

    public POIModel(){  
        new Thread(new Runnable() {
			public void run() {
				POIServer poi = new POIServer();
				mPOIs = poi.extractData();
			}
		}).start();    
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
