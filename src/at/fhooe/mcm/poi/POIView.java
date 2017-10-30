package at.fhooe.mcm.poi;

import javax.swing.*;
import java.awt.*;

public class POIView {

    private Panel mPanel;

    public POIView(POIController _controller) {


        mPanel = new Panel(new FlowLayout());



        Checkbox poiOne = new Checkbox("POI_1", false);
        Checkbox poiTwo = new Checkbox("POI_2", false);

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
