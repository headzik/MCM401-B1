package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.components.gis.GISController;

import java.awt.*;

public interface IUIView {
    Panel getView();

    void setController(GISController _controller);
}
