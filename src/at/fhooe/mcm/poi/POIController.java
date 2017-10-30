package at.fhooe.mcm.poi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class POIController implements ItemListener {

    private POIModel mModel;

    public POIController(POIModel _model){
        mModel = _model;
    }
    
    @Override
    public void itemStateChanged(ItemEvent _e) {
    	String poiName = _e.getItem().toString();
    	int state = _e.getStateChange();
    	
    	switch(poiName) {
    	case "POI_1" : {
    		if(isChecked(state)) {
    			//mModel.addPOI();
    		} else {
    			//mModel.removePOI();
    		}
    	}
    		break;
    	case "POI_2" :{
    		if(isChecked(state)) {
    			//mModel.addPOI();
    		} else {
    			//mModel.removePOI();
    		}
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
