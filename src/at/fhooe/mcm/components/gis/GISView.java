package at.fhooe.mcm.components.gis;

import at.fhooe.mcm.interfaces.IDataObserver;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.image.BufferedImage;

public class GISView implements IDataObserver {

	public DrawingPanel mPanel;
	private Panel mMainPanel, mButtonPanel;
	private GISController mController;
	private DrawingPanel panel;

	/**
	 * Constructor initializing GUI components.
	 * @param _controller Callback to MVC Controller
	 */
	public GISView(GISController _controller) {
		
		mController = _controller;

		mMainPanel = new Panel();
		mMainPanel.setLayout(new BorderLayout());
		mMainPanel.setBackground(Color.WHITE);

		mPanel= new DrawingPanel();
		mPanel.setBackground(Color.LIGHT_GRAY);


		mPanel.addComponentListener(mController);
		mPanel.addMouseListener(mController);
		mPanel.addMouseWheelListener(mController);
		mPanel.addMouseMotionListener(mController);
		mPanel.addKeyListener(mController);


		mMainPanel.add(mPanel,BorderLayout.CENTER);

	}

	/**
	 * Updates the view by drawing an image in DrawingPanel.
	 */
	@Override
	public void update(BufferedImage _data) {
		mPanel.drawImage(_data);
	}
	
	/**
	 * Redraw method for XOR mode.
	 * @param _rect Rectangle to redraw
	 */
	public void drawRect(Rectangle _rect) {
		Graphics g = mPanel.getGraphics();
		g.setXORMode(Color.ORANGE);
		g.drawRect(_rect.x, _rect.y, _rect.width, _rect.height);
		g.setPaintMode();
	}
	
	/**
	 * Method for moving/scrolling the map
	 * @param _deltaX x offset
	 * @param _deltaY y offset
	 */
	public void scrollRect(int _deltaX, int _deltaY) {
		Graphics g = mPanel.getGraphics();
		g.clearRect(0, 0, 10, mPanel.getHeight()); // Left
		g.clearRect(0, 0, mPanel.getWidth(), 10); // Top
		g.clearRect(mPanel.getWidth()-10, 0 , 10, mPanel.getHeight()); // Right
		g.clearRect(0, mPanel.getHeight() - 10, mPanel.getWidth(), 10); // Bottom		
		
		g.copyArea(0, 0, mPanel.getWidth(), mPanel.getHeight(), _deltaX, _deltaY);
	}

	public Panel getView(){
		return mMainPanel;
	}

	public void setPanel(Panel _panel) {
		mMainPanel.add(_panel, BorderLayout.SOUTH);
	}
}
