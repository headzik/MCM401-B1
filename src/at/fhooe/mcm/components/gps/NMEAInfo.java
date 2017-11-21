package at.fhooe.mcm.components.gps;

import java.util.ArrayList;

/**
 * Object containing all information parsed from NMEA data.
 * @author Admin
 */
public class NMEAInfo {
	
	private float mLatitude, mLongitude;
	private float mTime;
	private int mSatCount;
	private float mPDOP, mHDOP, mVDOP;
	private int mFixQuality;
	private float mHeight;
	private ArrayList<SatelliteInfo> mSatInfo;
	
	/**
	 * Constructor.
	 */
	public NMEAInfo() {
		mLatitude = 0;
		mLongitude = 0;
		mTime = 0;
		mSatCount = 0;
		mPDOP = 0;
		mHDOP = 0;
		mVDOP = 0;
		mFixQuality = 0;
		mHeight = 0;
		mSatInfo = new ArrayList<SatelliteInfo>();
	}

	public float getLatitude() {
		return mLatitude;
	}

	public void setLatitude(float _latitude) {
		this.mLatitude = _latitude;
	}

	public float getLongitude() {
		return mLongitude;
	}

	public void setLongitude(float _longitude) {
		this.mLongitude = _longitude;
	}

	public float getTime() {
		return mTime;
	}

	public void setTime(float _time) {
		this.mTime = _time;
	}

	public int getSatCount() {
		return mSatCount;
	}

	public void setSatCount(int _satCount) {
		this.mSatCount = _satCount;
	}

	public float getPDOP() {
		return mPDOP;
	}

	public void setPDOP(float _PDOP) {
		this.mPDOP = _PDOP;
	}

	public float getHDOP() {
		return mHDOP;
	}

	public void setHDOP(float _HDOP) {
		this.mHDOP = _HDOP;
	}

	public float getVDOP() {
		return mVDOP;
	}

	public void setVDOP(float _VDOP) {
		this.mVDOP = _VDOP;
	}

	public int getFixQuality() {
		return mFixQuality;
	}

	public void setFixQuality(int _fixQuality) {
		this.mFixQuality = _fixQuality;
	}

	public float getHeight() {
		return mHeight;
	}

	public void setHeight(float _height) {
		this.mHeight = _height;
	}

	public ArrayList<SatelliteInfo> getSatInfo() {
		return mSatInfo;
	}
	
	/**
	 * Adds a SatelliteInfo to the list.
	 * @param _info SatelliteInfo object.
	 */
	public void addSatInfo(SatelliteInfo _info) {
		mSatInfo.add(_info);
	}

	/**
	 * Checks if object is filled with data and returns true if it is.
	 * @return True if filled, false if not.
	 */
	public boolean isReadyForUpdate() {
		if (mLatitude != 0 && mLongitude != 0 && mTime != 0 && mSatCount != 0 && 
			mPDOP != 0 && mHDOP != 0 && mVDOP != 0 && mFixQuality != 0 && mHeight != 0 &&
			!mSatInfo.isEmpty())
			return true;
		return false;
	}
}
