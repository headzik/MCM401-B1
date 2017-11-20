package at.fhooe.mcm.gps;

/**
 * Object containing information from a satellite.
 *
 * @author Admin
 */
public class SatelliteInfo {
    private int mNoOfSatellite;
    private int mHorizontalAngle;
    private int mVerticalAngle;
    private int mSNR;
    private boolean mUsed;

    /**
     * Constructor.
     *
     * @param _noOfSat   Number of satellite
     * @param _hoAngle   Horizontal angle
     * @param _vertAngle Vertical angle
     * @param _snr       Signal to noise ratio
     * @param _used      Indicator if it is used or not
     */
    public SatelliteInfo(int _noOfSat, int _hoAngle, int _vertAngle, int _snr, boolean _used) {
        setNoOfSatellite(_noOfSat);
        setHorizontalAngle(_hoAngle);
        setVerticalAngle(_vertAngle);
        setSNR(_snr);
        mUsed = _used;
    }

    public int getNoOfSatellite() {
        return mNoOfSatellite;
    }

    public void setNoOfSatellite(int _noOfSatellite) {
        this.mNoOfSatellite = _noOfSatellite;
    }

    public int getHorizontalAngle() {
        return mHorizontalAngle;
    }

    public void setHorizontalAngle(int _horizontalAngle) {
        this.mHorizontalAngle = _horizontalAngle;
    }

    public int getVerticalAngle() {
        return mVerticalAngle;
    }

    public void setVerticalAngle(int _verticalAngle) {
        this.mVerticalAngle = _verticalAngle;
    }

    public int getSNR() {
        return mSNR;
    }

    public void setSNR(int _SNR) {
        this.mSNR = _SNR;
    }

    public boolean isUsed() {
        return mUsed;
    }

    public void setUsed(boolean _used) {
        this.mUsed = _used;
    }
}
