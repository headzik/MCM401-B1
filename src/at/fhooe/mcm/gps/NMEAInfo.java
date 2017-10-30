package at.fhooe.mcm.gps;

import java.util.Vector;

public class NMEAInfo {


    private double mLatitude;
    private double mLongitude;
    private String mTime;
    private int mSatelliteCount;
    private double mPdop;
    private double mHdop;
    private double mVdop;
    private int mfixQuality;
    private double mHeight;
    private Vector<SatelliteInfo> mSatelliteInfos;

    public NMEAInfo(double _latitude, double _longitude, String _time,
                    int _satelliteCount, double _pdop, double _hdop,
                    double _vdop, int _fixQuality, double _height, Vector<SatelliteInfo> _satelliteInfo){
        mLatitude = _latitude;
        mLongitude = _longitude;
        mTime = _time;
        mSatelliteCount = _satelliteCount;
        mPdop = _pdop;
        mHdop = _hdop;
        mVdop = _vdop;
        mfixQuality = _fixQuality;
        mHeight = _height;
        mSatelliteInfos = _satelliteInfo;
    }
    public double getLatitude() {
        return mLatitude;
    }
    public void setLatitude(double _mLatitude) {
        mLatitude = _mLatitude;
    }
    public double getLongitude() {
        return mLongitude;
    }
    public void setLongitude(double _mLongitude) {
        mLongitude = _mLongitude;
    }
    public String getTime() {
        return mTime;
    }
    public void setTime(String _mTime) {
        mTime = _mTime;
    }
    public int getSatelliteCount() {
        return mSatelliteCount;
    }
    public void setSatelliteCount(int _mSatelliteCount) {
        mSatelliteCount = _mSatelliteCount;
    }
    public double getPdop() {
        return mPdop;
    }
    public void setPdop(double _mPdop) {
        mPdop = _mPdop;
    }
    public double getHdop() {
        return mHdop;
    }
    public void setHdop(double _mHdop) {
        mHdop = _mHdop;
    }
    public double getVdop() {
        return mVdop;
    }
    public void setVdop(double _mVdop) {
        mVdop = _mVdop;
    }
    public int getfixQuality() {
        return mfixQuality;
    }
    public void setM_fixQuality(int _mfixQuality) {
        mfixQuality = _mfixQuality;
    }
    public double getHeight() {
        return mHeight;
    }
    public void setHeight(double _mHeight) {
        mHeight = _mHeight;
    }
    public Vector<SatelliteInfo> getSatelliteInfos() {
        return mSatelliteInfos;
    }
    public void setM_SatelliteInfos(Vector<SatelliteInfo> _mSatelliteInfos) {
        mSatelliteInfos = _mSatelliteInfos;
    }


}
