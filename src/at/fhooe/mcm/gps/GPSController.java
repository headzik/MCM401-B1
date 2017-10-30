package at.fhooe.mcm.gps;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GPSController implements ItemListener {

    GPSModel mModel;

    public GPSController(GPSModel m) {
        mModel = m;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    	int state = e.getStateChange();
	
		if(isChecked(state)) {
	        mModel.startService();
		} else {
	        mModel.stopService();
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
