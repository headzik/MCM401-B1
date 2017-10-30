package at.fhooe.mcm.gps;

import at.fhooe.mcm.interfaces.ISatInfo;

import java.awt.*;

public class GPSView implements ISatInfo {

    Label mLatitude;
    Label mLongitude;
    Label mTime;
    Label mSatCount;
    Label mPDOP;
    Label mHDOP;
    Label mVDOP;
    Label mFix;
    Label mHeight;
    Label mInfoSat;
    Panel mTextPanel = null;
    Panel mDrawing = null;
    Checkbox mToggle;

    public GPSView(GPSController _controller){

        mTextPanel = new Panel();
        mTextPanel.setLayout(new GridLayout(11, 1));

        mToggle = new Checkbox("ON",false);
        mLatitude = new Label();
        mLatitude.setText("Latitude: ");
        mLongitude = new Label();
        mLongitude.setText("Longitude: ");
        mTime = new Label();
        mTime.setText("Time: ");
        mSatCount = new Label();
        mSatCount.setText("Satellites: ");
        mPDOP = new Label();
        mPDOP.setText("PDOP: ");
        mHDOP = new Label();
        mHDOP.setText("HDOP: ");
        mVDOP = new Label();
        mVDOP.setText("VDOP: ");
        mFix = new Label();
        mFix.setText("Fix: ");
        mHeight = new Label();
        mHeight.setText("Height: ");
        mInfoSat = new Label();
        mInfoSat.setText("Info: ");

        mToggle.addItemListener(_controller);

        mTextPanel.add(mToggle);
        mTextPanel.add(mLatitude);
        mTextPanel.add(mLongitude);
        mTextPanel.add(mTime);
        mTextPanel.add(mSatCount);
        mTextPanel.add(mPDOP);
        mTextPanel.add(mHDOP);
        mTextPanel.add(mVDOP);
        mTextPanel.add(mFix);
        mTextPanel.add(mHeight);
        mTextPanel.add(mInfoSat);
    }

    @Override
    public void update(NMEAInfo _info) {
        mLatitude.setText("Latitude: "+_info.getLatitude());
        mLongitude.setText("Longitude: "+_info.getLongitude());
        mTime.setText("Time: "+_info.getTime().substring(0, 2)+":"+_info.getTime().substring(2,4)+":"+_info.getTime().substring(4, 6));

        mSatCount.setText("Satellites: "+_info.getSatelliteCount());
        mPDOP.setText("PDOP: "+_info.getPdop());
        mHDOP.setText("HDOP: "+_info.getHdop());
        mVDOP.setText("VDOP: "+_info.getVdop());
        mFix.setText("Fix: "+_info.getfixQuality());
        mHeight.setText("Height: "+_info.getHeight());

    }

    public Panel getView() {
        return mTextPanel;
    }
}
