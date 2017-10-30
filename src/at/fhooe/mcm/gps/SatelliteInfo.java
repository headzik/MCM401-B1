package at.fhooe.mcm.gps;

public class SatelliteInfo {
    int mID;
    int mWinkelV;
    int mWinkelH;
    int mSNR;

    public SatelliteInfo() {
        mID = 0;
        mWinkelV = 0;
        mWinkelH = 0;
        mSNR = 0;
    }

    public int getmWinkelH() {
        return mWinkelH;
    }
    public int getmID() {
        return mID;
    }
    public int getmWinkelV() {
        return mWinkelV;
    }
    public int getSNR() {
        return mSNR;
    }
    public void setmID(int _mID) {
        mID = _mID;
    }
    public void setmWinkelH(int _mWinkelH) {
        mWinkelH = _mWinkelH;
    }
    public void setmWinkelV(int _mWinkelV) {
        mWinkelV = _mWinkelV;
    }
    public void setSNR(int _SNR) {
        mSNR = _SNR;
    }
}
