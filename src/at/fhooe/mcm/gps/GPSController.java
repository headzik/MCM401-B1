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
        mModel.startService();
    }
}
