package at.fhooe.mcm.components.aal;

import javax.swing.*;
import java.awt.*;

/**
 * The view for the AAL Component. Holding all UI components.
 * @author ifumi
 *
 */
public class AALView {

    private Panel mPanel;
    private TextField mFilePath;
    private JComboBox mParserComboBox;

    /**
     * Constructor.
     * @param _controller The controller to use for the view.
     */
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

    /**
     * Getter for the AAL View.
     * @return
     */
    public Panel getView(){
        return mPanel;
    }

    /**
     * Setter for the file path.
     * @param _text the file path.
     */
    public void setFilePathText(String _text) {
        mFilePath.setText(_text);
    }

    /**
     * Getter for the file path.
     * @return
     */
    public  String getFilePathText(){
        return mFilePath.getText();
    }

    /**
     * Returns the selected parse mode.
     * @return The selected parse mode.
     */
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


