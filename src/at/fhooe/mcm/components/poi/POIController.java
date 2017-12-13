package at.fhooe.mcm.components.poi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;

/**
 * The controller for the POI component.
 * @author ifumi
 *
 */
public class POIController implements ItemListener {

    private POIModel mModel;

    /**
     * Constructor. 
     * @param _model The model of the component.
     */
    public POIController(POIModel _model) {
        mModel = _model;
    }

    /**
     * Called whenever the item state changes.
     */
    @Override
    public void itemStateChanged(ItemEvent _e) {
        String poiName = _e.getItem().toString();
        int state = _e.getStateChange();

        switch (poiName) {
            case "Police Stations": {
                if (isChecked(state)) {
                    mModel.setVisibleByType(true, POI_TYPE.POLICE);
                } else {
                    mModel.setVisibleByType(false, POI_TYPE.POLICE);
                }
            }
            break;
            case "Gas Stations": {
                if (isChecked(state)) {
                    mModel.setVisibleByType(true, POI_TYPE.GAS);
                } else {
                    mModel.setVisibleByType(false, POI_TYPE.GAS);
                }
            }
            break;
        }
        mModel.update();
    }

    public boolean isChecked(int state) {
        if (state == 1) {
            return true;
        } else {
            return false;
        }
    }
}
