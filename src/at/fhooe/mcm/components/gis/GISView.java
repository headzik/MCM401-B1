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
	private Panel mMainPanel;
	private Button mLoadButton, mZTFButton, mZoomInButton, 
				   mZoomOutButton, mScrollUpButton, mScrollDownButton, 
				   mScrollLeftButton, mScrollRightButton, mRotateLeftButton, 
				   mRotateRightButton, mPOIToggleButton, mStickyToggleButton,
				   mSaveImageButton;
	private Choice mChoice;
	private TextField mScale;
	private GISController mController;

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

		Panel buttonPanel = new Panel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new FlowLayout());

		mLoadButton = new Button("Load");
		mZTFButton = new Button("ZTF");
		mZoomInButton = new Button("+");
		mZoomOutButton = new Button("-");
		mScrollUpButton = new Button("Up");
		mScrollDownButton = new Button("Down");
		mScrollLeftButton = new Button("Left");
		mScrollRightButton = new Button("Right");
		mRotateLeftButton = new Button("Rot<");
		mRotateRightButton = new Button("Rot>");
		mPOIToggleButton = new Button("POI");
		mStickyToggleButton = new Button("Sticky");
		mSaveImageButton = new Button("Store");
		mScale = new TextField("1:unknown");

		buttonPanel.add(mLoadButton);
		buttonPanel.add(new Panel());
		buttonPanel.add(mZTFButton);
		buttonPanel.add(mZoomInButton);
		buttonPanel.add(mZoomOutButton);
		buttonPanel.add(new Panel());
		buttonPanel.add(mScrollUpButton);
		buttonPanel.add(mScrollLeftButton);
		buttonPanel.add(mScrollDownButton);
		buttonPanel.add(mScrollRightButton);
		buttonPanel.add(new Panel());
		buttonPanel.add(mRotateLeftButton);
		buttonPanel.add(mRotateRightButton);
		buttonPanel.add(new Panel());
		buttonPanel.add(mPOIToggleButton);
		buttonPanel.add(mScale);


		mPanel.addComponentListener(mController);
		mPanel.addMouseListener(mController);
		mPanel.addMouseWheelListener(mController);
		mPanel.addMouseMotionListener(mController);
		mPanel.addKeyListener(mController);
		mLoadButton.addActionListener(mController);
		mZTFButton.addActionListener(mController);
		mZoomInButton.addActionListener(mController);
		mZoomOutButton.addActionListener(mController);
		mScrollUpButton.addActionListener(mController);
		mScrollDownButton.addActionListener(mController);
		mScrollLeftButton.addActionListener(mController);
		mScrollRightButton.addActionListener(mController);
		mRotateLeftButton.addActionListener(mController);
		mRotateRightButton.addActionListener(mController);
		mPOIToggleButton.addActionListener(mController);
		mStickyToggleButton.addActionListener(mController);
		mSaveImageButton.addActionListener(mController);
		mScale.addActionListener(mController);

		mMainPanel.add(mPanel,BorderLayout.CENTER);
		mMainPanel.add(buttonPanel, BorderLayout.SOUTH);

	}

	/**
	 * Updates the view by drawing an image in DrawingPanel.
	 */
	@Override
	public void update(BufferedImage _data) {
		mPanel.drawImage(_data);
		mScale.setText("1:" + mController.getScale());
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

}
