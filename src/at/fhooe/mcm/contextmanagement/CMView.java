package at.fhooe.mcm.contextmanagement;

import javax.swing.*;
import java.awt.*;

public class CMView {
    private Panel mPanel;

    public CMView(CMController _controller) {
        mPanel = new Panel(new GridLayout(2, 2));


        // setup time panel
        Panel timePanel = new Panel(new FlowLayout());
        String[] hoursStrings = new String[24];
        for (int i = 0; i < hoursStrings.length; i++) {
            hoursStrings[i] = String.valueOf(i);
        }
        String[] minutesString = new String[60];
        for (int i = 0; i < minutesString.length; i++) {
            minutesString[i] = String.valueOf(i);
        }
        JComboBox hours = new JComboBox(hoursStrings);
        JComboBox minutes = new JComboBox(minutesString);
        Label hoursLabel = new Label("H");
        Label minutesLabel = new Label("M");
        Button setTime = new Button("Set Time");
        setTime.setActionCommand("time");

        timePanel.add(hours);
        timePanel.add(hoursLabel);
        timePanel.add(minutes);
        timePanel.add(minutesLabel);
        timePanel.add(setTime);

        // setup time panel

        mPanel.add(timePanel);

    }

    public Panel getView() {
        return mPanel;
    }
}
