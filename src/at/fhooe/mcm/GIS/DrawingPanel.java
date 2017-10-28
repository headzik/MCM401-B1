package at.fhooe.mcm.GIS;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

@SuppressWarnings("serial")
public class DrawingPanel extends Panel {

	private Image mImage = null;
	
	public DrawingPanel() { }
	
	public void setImage(Image _img) {
		mImage = _img;
	}
	 
	public void paint(Graphics _g) {   
		_g.drawImage(mImage, 0, 0, getParent()); 
	}
}
