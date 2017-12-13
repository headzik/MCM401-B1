package at.fhooe.mcm.components.poi;

import javax.swing.*;
import java.awt.*;

/**
 * The view for the POI component.
 * @author ifumi
 *
 */
public class POIView {

    private Panel mPanel;

    /**
     * The constructor.
     * @param _controller The controller to use for the view.
     */
    public POIView(POIController _controller) {

        mPanel = new Panel(new FlowLayout());

        Checkbox poiOne = new Checkbox("Police Stations", false);
        Checkbox poiTwo = new Checkbox("Gas Stations", false);

        // add listeners
        poiOne.addItemListener(_controller);
        poiTwo.addItemListener(_controller);

        mPanel.add(poiOne);
        mPanel.add(poiTwo);

    }

    /**
     * Getter for the poi view.
     * @return
     */
    public Panel getView() {
        return mPanel;
    }

}
