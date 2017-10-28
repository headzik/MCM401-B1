package at.fhooe.mcm.components;

import at.fhooe.mcm.interfaces.IComponent;

import java.awt.*;

public class GPSComponent implements IComponent {
    @Override
    public Panel getView() {
		Panel p = new Panel();
		p.setBackground(Color.BLUE);

		return p;
    }

    @Override
    public String getName() {
        return "GPSComponent";
    }
}
