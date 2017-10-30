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
    private POIObject p1, p2;

    public POIController(POIModel _model){
        mModel = _model;    
        
        // Add hardcoded poi objects (2 different types) for testing
        Point p = new Point(-754069, 8863172);
		p1 = new POIObject("1", POI_TYPE, new Polygon(new int[]{(int)p.getX()},new int[]{(int)p.getY()},1), loadImage("resources/1.png"), POIObject.POI_TYPE.TYPE_1);
		p2 = new POIObject("1", POI_TYPE, new Polygon(new int[]{(int)p.getX()},new int[]{(int)p.getY()},1), loadImage("resources/2.png"), POIObject.POI_TYPE.TYPE_2);
		mModel.addPOI(p1);
		mModel.addPOI(p2);
    }
    
    @Override
    public void itemStateChanged(ItemEvent _e) {
    	String poiName = _e.getItem().toString();
    	int state = _e.getStateChange();
    	
    	switch(poiName) {
    	case "POI_1" : {
    		if(isChecked(state)) {
				mModel.setVisibleByType(true, POIObject.POI_TYPE.TYPE_1);
			} else {
				mModel.setVisibleByType(false, POIObject.POI_TYPE.TYPE_1);
    		}
    	}
    		break;
    	case "POI_2" :{
    		if(isChecked(state)) {
    			mModel.setVisibleByType(true, POIObject.POI_TYPE.TYPE_2);
    		} else {
    			mModel.setVisibleByType(false, POIObject.POI_TYPE.TYPE_2);
    		}
    	}
    		break;
    	}
    	mModel.update();
    	
    }

	private BufferedImage loadImage(String _s) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(_s);
			return ImageIO.read(input);
		} catch(IOException _ex) {
			System.out.println("Error while loading pic");
		}
		return null;
	}
    
    public boolean isChecked(int state) {
    	if(state == 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
