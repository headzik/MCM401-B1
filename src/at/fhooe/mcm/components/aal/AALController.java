package at.fhooe.mcm.components.aal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the AAL Component. Handling all user inputs.
 * @author ifumi
 *
 */
public class AALController implements ActionListener {
    private AALView mView;
    private AALModel mModel;

    /**
     * Constructor.
     * @param _model
     */
    public AALController(AALModel _model) {
        mModel = _model;
    }

    /**
     * Called whenever an action is performed (buttons are pressed).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "open":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                if (fileChooser.showOpenDialog(mView.getView()) == JFileChooser.APPROVE_OPTION)
                    mView.setFilePathText(fileChooser.getSelectedFile().getAbsolutePath());
                break;
            case "parse":
                mModel.parseContextFile(mView.getFilePathText(), mView.getSelectedParser());
                break;
        }

    }

    /**
     * Setter for the AAL View.
     * @param view The view to set.
     */
    public void setView(AALView view) {
        mView = view;
    }
}
