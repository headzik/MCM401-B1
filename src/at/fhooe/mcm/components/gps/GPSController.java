package at.fhooe.mcm.components.gps;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import at.fhooe.mcm.components.poi.POIObject;

/**
 * The controller for the GPS component.
 * @author ifumi
 *
 */
public class GPSController implements ItemListener {
	private GPSModel mModel;
	
	/**
	 * The constructor.
	 * @param _model The model of the component.
	 */
	public GPSController(GPSModel _model) {
		mModel = _model;
	}
	
	/**
	 * Called whenever an item state is changed.
	 */
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
	
	/**
	 * Returns true if the GPS component (pos. sim.) is activated. 
	 * @param state The state to check
	 * @return The if checked, false otherwise.
	 */
	public boolean isChecked(int state) {
    	if(state == 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
