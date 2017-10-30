package at.fhooe.mcm.poi;

import java.util.List;

public class POIModel {

    private List<POIObject> mPOIs;

    public POIModel(){

    }

    public void addPOI(POIObject _poi){
        mPOIs.add(_poi);
    }
}
