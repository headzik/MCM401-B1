package at.fhooe.mcm.gps;

import at.fhooe.mcm.interfaces.IPositionUpdateListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

/**
 * DataView Panel.
 * @author Admin
 */
@SuppressWarnings("serial")
public class DataView extends Panel implements IPositionUpdateListener {
	
	Label mLat, mLong, mTime, mPDOP, mHDOP, mVDOP, mFixQuality, mHeight;
	NMEAInfo mInfo;

	private final int mFontSize = 20;

	/**
	 * Constructor.
	 */
	public DataView() {
		this.setSize(500,500);
		this.setBackground(Color.BLACK);
		
		mLat = new Label("Latitude: ");
		mLat.setForeground(Color.WHITE);
		mLat.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mLong = new Label("Longitude: ");
		mLong.setForeground(Color.WHITE);
		mLong.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mTime = new Label("Time: ");
		mTime.setForeground(Color.WHITE);
		mTime.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mPDOP = new Label("PDOP: ");
		mPDOP.setForeground(Color.WHITE);
		mPDOP.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mHDOP = new Label("HDOP: ");
		mHDOP.setForeground(Color.WHITE);
		mHDOP.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mVDOP = new Label("VDOP: ");
		mVDOP.setForeground(Color.WHITE);
		mVDOP.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mFixQuality = new Label("Fix Quality: ");
		mFixQuality.setForeground(Color.WHITE);
		mFixQuality.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		mHeight = new Label("Height: ");
		mHeight.setForeground(Color.WHITE);
		mHeight.setFont(new Font("Arial", Font.PLAIN, mFontSize));
		
		this.setLayout(new GridLayout(8, 1));
		
		this.add(mLat);
		this.add(mLong);
		this.add(mTime);
		this.add(mPDOP);
		this.add(mHDOP);
		this.add(mVDOP);
		this.add(mFixQuality);
		this.add(mHeight);
	}

	/**
	 * Update method. Called when NMEAInfo gets updated.
	 */
	@Override
	public void updateSats(NMEAInfo _mInfo) {
		mInfo = _mInfo;
		repaint();
	}
	
	/**
	 * Paint method - handles all painting
	 */
	@Override
	public void paint(Graphics _g) {
		if (mInfo != null) {
			
			mLat.setText("Latitude: " + mInfo.getLatitude());
			mLong.setText("Longitude: " + mInfo.getLongitude());
			
			int time = (int)mInfo.getTime();
			
			int sec = time % 100;
			time /= 100;
			int min = time % 100;
			time /= 100;
			int hours = time % 100;	
			
			mTime.setText("Time: " + String.format("%2d:%2d:%2d", hours, min, sec).replace(" ", "0"));
			mPDOP.setText("PDOP: " + mInfo.getPDOP());
			mHDOP.setText("HDOP: " + mInfo.getHDOP());
			mVDOP.setText("VDOP: " + mInfo.getVDOP());
			mFixQuality.setText("Fix Quality: " + mInfo.getFixQuality());
			mHeight.setText("Height: " + mInfo.getHeight());	
			super.paint(_g);
		}
	}
}


