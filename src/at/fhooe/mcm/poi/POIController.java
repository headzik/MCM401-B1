package at.fhooe.mcm.poi;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class POIController implements ItemListener {

    private POIModel mModel;

    public POIController(POIModel _model){
        mModel = _model;
    }

    @Override
    public void itemStateChanged(ItemEvent _e) {
    }
}
