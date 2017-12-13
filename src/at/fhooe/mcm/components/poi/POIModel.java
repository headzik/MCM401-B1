package at.fhooe.mcm.components.poi;

import at.fhooe.mcm.components.poi.POI_TYPE;
import at.fhooe.mcm.objects.Observable;

import java.util.List;

/**
 * The model for the POI component.
 * @author ifumi
 *
 */
public class POIModel extends Observable {

    private List<POIObject> mPOIs;

    /**
     * The constructor.
     */
    public POIModel() {
        new Thread(new Runnable() {
            public void run() {
                POIServer poi = new POIServer();
                mPOIs = poi.extractData();
            }
        }).start();
    }

    /** 
     * The update method.
     */
    public void update() {
        if (mPOIs != null) {
            for (POIObject poi : mPOIs) {
                notifyObservers(poi);
            }
        }

        notifyObservers(null);
    }

    /**
     * Changes visibility of all POIs with the specific type.
     *
     * @param _visible Visibility of type
     * @param _type    Type of POI to change visibility for
     */
    public void setVisibleByType(boolean _visible, POI_TYPE _type) {
        if (mPOIs != null) {
            for (POIObject poi : mPOIs) {
                if (poi.getPOIType() == _type)
                    poi.setVisible(_visible);
            }
        }
        update();
    }
}
