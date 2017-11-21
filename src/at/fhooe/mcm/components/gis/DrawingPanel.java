package at.fhooe.mcm.components.gis;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
	
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
