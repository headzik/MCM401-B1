package at.fhooe.mcm.components.gis.ui;

import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.interfaces.IUIView;

import java.awt.*;


public class CarUIView implements IUIView {


    private Panel mButtonPanel;
    private Button mLoadButton, mZTFButton, mZoomInButton,
            mZoomOutButton, mScrollUpButton, mScrollDownButton,
            mScrollLeftButton, mScrollRightButton;
    private GISController mController;

    public CarUIView() {
    }

    @Override
    public void setController(GISController _controller) {
        mController = _controller;
        setup();
    }


    public void setup() {

        mButtonPanel = new Panel();
        mButtonPanel.setBackground(Color.WHITE);
        mButtonPanel.setLayout(new FlowLayout());

        mLoadButton = new Button("Load");
        mZTFButton = new Button("ZTF");
        mZoomInButton = new Button("+");
        mZoomOutButton = new Button("-");
        mScrollUpButton = new Button("Up");
        mScrollDownButton = new Button("Down");
        mScrollLeftButton = new Button("Left");
        mScrollRightButton = new Button("Right");

        mButtonPanel.add(mLoadButton);
        mButtonPanel.add(new Panel());
        mButtonPanel.add(mZTFButton);
        mButtonPanel.add(mZoomInButton);
        mButtonPanel.add(mZoomOutButton);
        mButtonPanel.add(new Panel());
        mButtonPanel.add(mScrollUpButton);
        mButtonPanel.add(mScrollLeftButton);
        mButtonPanel.add(mScrollDownButton);
        mButtonPanel.add(mScrollRightButton);

        mLoadButton.addActionListener(mController);
        mZTFButton.addActionListener(mController);
        mZoomInButton.addActionListener(mController);
        mZoomOutButton.addActionListener(mController);
        mScrollUpButton.addActionListener(mController);
        mScrollDownButton.addActionListener(mController);
        mScrollLeftButton.addActionListener(mController);
        mScrollRightButton.addActionListener(mController);
    }

    public Panel getView() {
        return mButtonPanel;
    }
}
