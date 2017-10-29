package at.fhooe.mcm.gis;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class DrawingPanel extends Panel {
	
	private boolean mDraw = false;
	private BufferedImage mImg;

	/**
	 * Constructor
	 */
	public DrawingPanel() {
		super();
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics _g) {
		super.paint(_g);
		if (mDraw && mImg != null) {
			_g.drawImage(mImg, 0, 0, null);
		}
	}
	
	/**
	 * Draws given image to panel.
	 */
	public void drawImage(BufferedImage _img) {
		mDraw = true;
		mImg = _img;
		repaint();
	}
	
}
