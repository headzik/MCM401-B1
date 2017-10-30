package at.fhooe.mcm.gps;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GPSReceiverSim extends BufferedReader {

    int mSleep;
    String mFilter;

    public GPSReceiverSim(String _filename, int _sleep, String _filter) throws FileNotFoundException {
        super(new FileReader(new File(_filename)));

        mSleep = _sleep;
        mFilter = _filter;

        readLine();

    }

    public String readLine(){
        String out = null;

        try {
            out = super.readLine();
            if (out == null) {
                return null;
            }
            if(out.startsWith(mFilter)){
                Thread.sleep(mSleep);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return out;

    }

    public String getFilter() {
        return mFilter;
    }

}
