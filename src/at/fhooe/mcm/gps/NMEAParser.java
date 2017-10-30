package at.fhooe.mcm.gps;


import at.fhooe.mcm.interfaces.ISatInfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NMEAParser {

    String mLine = "";
    String[] mLineArray = null;
    GPSReceiverSim mSim;
    double mGradLat;
    double mGradLon;
    double mMinutes;
    String mTime;
    int mSatellite;
    double mPDOP;
    double mHDOP;
    double mVDOP;
    int mFix;
    double mHeight;
    Vector<SatelliteInfo> mSatelliteInfos;
    NMEAInfo mInfo;
    List<ISatInfo> mSatInfo = new ArrayList<ISatInfo>();


    public NMEAParser() throws FileNotFoundException {
        mSim = new GPSReceiverSim("GPS-Log-I.log", 500, "$GPGGA");
    }

    public void getLatitude() {
        mGradLat = Double.parseDouble(mLineArray[2].substring(0, 2));
        mMinutes = Double.parseDouble(mLineArray[2].substring(2, mLineArray[2].length()));
        mMinutes /= 60;
        mGradLat += mMinutes;
    }

    public void getLongitude() {
        mGradLon = Double.parseDouble(mLineArray[4].substring(0, 3));
        mMinutes = Double.parseDouble(mLineArray[4].substring(3, mLineArray[4].length()));
        mMinutes /= 60;
        mGradLon += mMinutes;
    }

    public void addListener(ISatInfo _data){
        mSatInfo.add(_data);
    }

    public void getData() {
        while (mLine != null) {
            if (mLine.startsWith(mSim.getFilter())) {
                mLineArray = mLine.split(",");
                if (!mLineArray[1].isEmpty()) {
                    mTime = mLineArray[1];
                }
                if (!mLineArray[2].isEmpty() && !mLineArray[4].isEmpty()) {
                    getLatitude();
                    getLongitude();
                }
                if (!mLineArray[6].isEmpty()) {
                    mFix = Integer.parseInt(mLineArray[6]);
                }
                if (!mLineArray[7].isEmpty()) {
                    mSatellite = Integer.parseInt(mLineArray[7]);
                }
                if (!mLineArray[9].isEmpty()) {
                    mHeight = Double.parseDouble(mLineArray[9]);
                }

            } else if (mLine.startsWith("$GPGSA")) {
                mLineArray = mLine.split(",");
                if (!mLineArray[15].isEmpty()) {
                    mPDOP = Double.parseDouble(mLineArray[15]);
                }
                if (!mLineArray[16].isEmpty()) {
                    mHDOP = Double.parseDouble(mLineArray[16]);
                }
                if (!mLineArray[17].isEmpty() && mLineArray[17].length() >= 3) {
                    mVDOP = Double.parseDouble(mLineArray[17].substring(0, mLineArray[17].length() - 3));
                }
            } else if (mLine.startsWith("$GPGSV")) {
                mSatelliteInfos = new Vector<>();
                SatelliteInfo satellite = new SatelliteInfo();
                mLineArray = mLine.split(",");
                for (int i = 0; i < 19; i = i + 4) {
                    if (mLineArray.length >= i+7) {
                        if (!mLineArray[i + 4].isEmpty() && !mLineArray[i+4].contains("*")) {
                            satellite.setmID(Integer.parseInt(mLineArray[i+4]));
                        }
                        if (!mLineArray[i + 5].isEmpty() && !mLineArray[i+5].contains("*")) {
                            satellite.setmWinkelV(Integer.parseInt(mLineArray[i+5]));
                        }
                        if (!mLineArray[i + 6].isEmpty() && !mLineArray[i+6].contains("*")) {
                            satellite.setmWinkelH(Integer.parseInt(mLineArray[i+6]));
                            ;
                        }
                        if (!mLineArray[i + 7].isEmpty() && !mLineArray[i+7].substring(0, 2).contains("*")) {
                            satellite.setSNR(Integer.parseInt(mLineArray[i+7].substring(0, 2)));
                        }
                        mSatelliteInfos.addElement(satellite);
                    }
                }
                mInfo = new NMEAInfo(mGradLat, mGradLon, mTime, mSatellite, mPDOP, mHDOP, mVDOP, mFix, mHeight,
                        mSatelliteInfos);
                update();
            }

            mLine = mSim.readLine();
        }
    }

    private void update() {
        for(ISatInfo info : mSatInfo){
            info.update(mInfo);
        }
    }
}