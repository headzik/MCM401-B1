package at.fhooe.mcm.components.gps;

import java.awt.*;

public class GPSView {

    private Panel mPanel;
    private DataView dataView;
    private SatView satView;
    private Checkbox activated;

    public GPSView(GPSController _controller, NMEAParser _parser) {
        mPanel = new Panel();
        dataView = new DataView();
        satView = new SatView();

        mPanel.setLayout(new BorderLayout());
        ;
        activated = new Checkbox("ON", false);

        mPanel.add(activated, BorderLayout.NORTH);
        mPanel.add(satView, BorderLayout.CENTER);
        mPanel.add(dataView, BorderLayout.LINE_END);

        activated.addItemListener(_controller);
        _parser.addListener(dataView);
        _parser.addListener(satView);
    }

    public Panel getView() {
        return mPanel;
    }
}
