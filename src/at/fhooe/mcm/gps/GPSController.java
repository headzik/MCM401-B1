package at.fhooe.mcm.gps;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import at.fhooe.mcm.poi.POIObject;

public class GPSController implements ItemListener {
	
	private GPSModel mModel;
	
	public GPSController(GPSModel _model) {
		mModel = _model;
	}
	
	@Override
	public void itemStateChanged(ItemEvent _e) {
		String poiName = _e.getItem().toString();
    	int state = _e.getStateChange();
    	
    	switch(poiName) {
    	case "ON" : 
    		if(isChecked(state)) {
				mModel.startParsing();
			} else {
				mModel.stopParsing();
    		}  	
    		break;
		}
	}
	
	public boolean isChecked(int state) {
    	if(state == 1) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
