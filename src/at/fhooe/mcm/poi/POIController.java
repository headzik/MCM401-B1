package at.fhooe.mcm.poi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static at.fhooe.mcm.gis.DrawingContext.POI_TYPE;

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
			Point p = new Point(-754069, 8863172);
			POIObject p1 = new POIObject("1", POI_TYPE, new Polygon(new int[]{(int)p.getX()},new int[]{(int)p.getY()},1), loadImage("resources/1.png"));
    		if(isChecked(state)) {
				mModel.addPOI(p1);
			} else {
    			mModel.removePOI(p1);
    		}
    	}
    		break;
    	case "POI_2" :{
			Point p = new Point(-760630,8734278);
			POIObject p2 = new POIObject("1", POI_TYPE, new Polygon(new int[]{(int)p.getX()},new int[]{(int)p.getY()},1), loadImage("resources/2.png"));
    		if(isChecked(state)) {
    			mModel.addPOI(p2);
    		} else {
    			mModel.removePOI(p2);
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
