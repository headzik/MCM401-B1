package at.fhooe.mcm.aal;

import javax.swing.*;
import java.awt.*;

public class AALView {

    private Panel mPanel;

    public AALView(AALController _controller) {
        mPanel = new Panel(new FlowLayout());

        String[] parserStrings = { "DOM", "STREAM"};
        JComboBox parserComboBox = new JComboBox(parserStrings);
        parserComboBox.setActionCommand("ComboBox");
        parserComboBox.addActionListener(_controller);

        JButton parseButton = new JButton("Parse");
        parseButton.setActionCommand("Parse");
        parseButton.addActionListener(_controller);

        mPanel.add(parserComboBox);
        mPanel.add(parseButton);
    }

    public Panel getView(){
        return mPanel;
    }
}


