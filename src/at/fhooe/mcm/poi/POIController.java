package at.fhooe.mcm.poi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import static at.fhooe.mcm.gis.DrawingContext.POI_TYPE;

public class POIController implements ItemListener {

    private POIModel mModel;

    public POIController(POIModel _model) {
        mModel = _model;
    }

    @Override
    public void itemStateChanged(ItemEvent _e) {
        String poiName = _e.getItem().toString();
        int state = _e.getStateChange();

        switch (poiName) {
            case "POI_1": {
                if (isChecked(state)) {
                    mModel.setVisibleByType(true, POIObject.POI_TYPE.TYPE_1);
                } else {
                    mModel.setVisibleByType(false, POIObject.POI_TYPE.TYPE_1);
                }
            }
            break;
            case "POI_2": {
                if (isChecked(state)) {
                    mModel.setVisibleByType(true, POIObject.POI_TYPE.TYPE_2);
                } else {
                    mModel.setVisibleByType(false, POIObject.POI_TYPE.TYPE_2);
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
