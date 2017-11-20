package at.fhooe.mcm.gps;

import at.fhooe.mcm.interfaces.IPositionUpdateListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;

/**
 * Satellite View Panel
 * @author Admin
 */
@SuppressWarnings("serial")
public class SatView extends Panel implements IPositionUpdateListener {
	
	private final int mDiam = 500;
	private final int mSatDiam = 30;
	
	private NMEAInfo mInfo = null;

	/**
	 * Constructor.
	 */
	public SatView() {
		this.setSize(500, 500);
		this.setBackground(Color.BLACK);		
	}
	
	/**
	 * Update method. Updates the info object and writes new data to the labels.
	 */
	public void updateSats(NMEAInfo _info) {
		mInfo = _info;
        this.repaint();
	}
        	
	/**
	 * Paint method - handles all painting
	 */
	@Override
	public void paint(Graphics _g) {
		// Draw outer circle
		_g.setColor(Color.WHITE);
		_g.drawOval(0, 0, mDiam, mDiam);
		// Draw inner circle
		_g.drawOval(mDiam/2 - getRadius(45), mDiam/2 - getRadius(45), getRadius(45) * 2, getRadius(45)* 2);
		
		if (mInfo != null) {
			ArrayList<SatelliteInfo> sats = mInfo.getSatInfo();
			
			for (SatelliteInfo sat: sats) {
				
				int radius = (int) getRadius(sat.getVerticalAngle());
				int x =  (int) (radius * Math.sin(Math.toRadians(-sat.getHorizontalAngle() + 180)));
				int y =  (int) (radius * Math.cos(Math.toRadians(-sat.getHorizontalAngle() + 180)));
				
				if (sat.isUsed() && sat.getSNR() != 0) {
					_g.setColor(Color.GREEN);
				} else if (sat.getSNR() == 0) {
					_g.setColor(Color.RED);
				} else {
					_g.setColor(Color.BLUE);
				}
				
				_g.fillOval((mDiam/2) + x - mSatDiam/2, (mDiam/2) + y - mSatDiam/2, mSatDiam, mSatDiam);
				
				_g.setColor(Color.WHITE);
				_g.setFont(new Font("Arial", Font.PLAIN, 20));
				_g.drawString(Integer.toString(sat.getNoOfSatellite()), (mDiam/2) + x - 7, (mDiam/2) + y + 7);
				
			}
		}
		super.paint(_g);
	}
	
	/**
	 * Returns the radius for the given vertical angle.
	 * @param _angle Vertical angle
	 * @return Radius for given angle
	 */
	public int getRadius(float _angle) {
		return (int) (Math.cos((_angle*Math.PI)/180) * mDiam/2);
	}

}
