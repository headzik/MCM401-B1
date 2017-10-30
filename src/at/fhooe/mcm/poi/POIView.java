package at.fhooe.mcm.poi;

import javax.swing.*;
import java.awt.*;

public class POIView {

    private Panel mPanel;

    public POIView(POIController _controller) {


        mPanel = new Panel(new FlowLayout());



        JCheckBox poiOne = new JCheckBox("POI_1", false);
        JCheckBox poiTwo = new JCheckBox("POI_2", false);

        // add listeners
        poiOne.addItemListener(_controller);
        poiTwo.addItemListener(_controller);

        mPanel.add(poiOne);
        mPanel.add(poiTwo);

    }

    public Panel getView() {
        return mPanel;
    }

}
