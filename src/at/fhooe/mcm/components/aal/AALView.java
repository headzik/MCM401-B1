package at.fhooe.mcm.components.aal;

import at.fhooe.mcm.components.AALComponent;

import javax.swing.*;
import java.awt.*;

public class AALView {

    private Panel mPanel;
    private TextField mFilePath;
    private JComboBox mParserComboBox;

    public AALView(AALController _controller) {
        mPanel = new Panel(new FlowLayout());

        String[] parserStrings = { "DOM", "STREAM"};
        mParserComboBox = new JComboBox(parserStrings);
        mParserComboBox.setActionCommand("combobox");
        mParserComboBox.addActionListener(_controller);

        JButton parseButton = new JButton("Parse");
        parseButton.setActionCommand("parse");
        parseButton.addActionListener(_controller);

        JButton openFile = new JButton("Open file");
        openFile.setActionCommand("open");
        openFile.addActionListener(_controller);

        mFilePath = new TextField("",30);


        mPanel.add(mFilePath);
        mPanel.add(openFile);
        mPanel.add(mParserComboBox);
        mPanel.add(parseButton);
    }

    public Panel getView(){
        return mPanel;
    }

    public void setFilePathText(String _text) {
        mFilePath.setText(_text);
    }

    public  String getFilePathText(){
        return mFilePath.getText();
    }

    public AALModel.ParseMode getSelectedParser() {
        switch (mParserComboBox.getSelectedIndex()){
            case 0:
                return AALModel.ParseMode.DOM;
            case 1:
                return AALModel.ParseMode.STREAM;
            default:
                return AALModel.ParseMode.DOM;
        }
    }
}


