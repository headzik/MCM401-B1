package at.fhooe.mcm.components.gis.ui;

import at.fhooe.mcm.components.gis.GISController;
import at.fhooe.mcm.interfaces.IUIView;

import java.awt.*;

public class PedestrianUIView implements IUIView {

    private Panel mMainPanel;
    private Button mLoadButton, mZTFButton, mZoomInButton,
            mZoomOutButton, mScrollUpButton, mScrollDownButton,
            mScrollLeftButton, mScrollRightButton, mRotateLeftButton,
            mRotateRightButton, mPOIToggleButton, mStickyToggleButton,
            mSaveImageButton;
    private GISController mController;

    public PedestrianUIView(){

    }
    @Override
    public Panel getView() {
        return mMainPanel;
    }

    @Override
    public void setController(GISController _controller) {
        mController = _controller;
        setup();
    }

    public void setup() {


        mMainPanel = new Panel();
        mMainPanel.setBackground(Color.WHITE);
        mMainPanel.setLayout(new FlowLayout());

        mLoadButton = new Button("Load");
        mZTFButton = new Button("ZTF");
        mZoomInButton = new Button("+");
        mZoomOutButton = new Button("-");
        mScrollUpButton = new Button("Up");
        mScrollDownButton = new Button("Down");
        mScrollLeftButton = new Button("Left");
        mScrollRightButton = new Button("Right");
        mRotateLeftButton = new Button("Rot<");
        mRotateRightButton = new Button("Rot>");
        mPOIToggleButton = new Button("POI");
        mStickyToggleButton = new Button("Sticky");
        mSaveImageButton = new Button("Store");

        mMainPanel.add(mLoadButton);
        mMainPanel.add(new Panel());
        mMainPanel.add(mZTFButton);
        mMainPanel.add(mZoomInButton);
        mMainPanel.add(mZoomOutButton);
        mMainPanel.add(new Panel());
        mMainPanel.add(mScrollUpButton);
        mMainPanel.add(mScrollLeftButton);
        mMainPanel.add(mScrollDownButton);
        mMainPanel.add(mScrollRightButton);
        mMainPanel.add(new Panel());
        mMainPanel.add(mRotateLeftButton);
        mMainPanel.add(mRotateRightButton);
        mMainPanel.add(new Panel());
        mMainPanel.add(mPOIToggleButton);


        mLoadButton.addActionListener(mController);
        mZTFButton.addActionListener(mController);
        mZoomInButton.addActionListener(mController);
        mZoomOutButton.addActionListener(mController);
        mScrollUpButton.addActionListener(mController);
        mScrollDownButton.addActionListener(mController);
        mScrollLeftButton.addActionListener(mController);
        mScrollRightButton.addActionListener(mController);
        mRotateLeftButton.addActionListener(mController);
        mRotateRightButton.addActionListener(mController);
        mPOIToggleButton.addActionListener(mController);
        mStickyToggleButton.addActionListener(mController);
        mSaveImageButton.addActionListener(mController);

    }
}
