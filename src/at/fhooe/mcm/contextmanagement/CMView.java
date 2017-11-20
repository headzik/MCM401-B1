package at.fhooe.mcm.contextmanagement;

import java.awt.*;

public class CMView {
    private Panel mPanel;

    public CMView(CMController _controller) {
        mPanel = new Panel(new GridLayout());

    }

    public Panel getView() {
        return mPanel;
    }
}
