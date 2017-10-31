package at.fhooe.mcm.gis;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;


public class GISController extends WindowAdapter implements ActionListener,
        ComponentListener,
        MouseListener,
        MouseWheelListener,
        MouseMotionListener,
        KeyListener,
        ItemListener {

    private GISModel mModel;
    private GISView mView;

    private final double mZoomFactor = 1.3;
    private final int mTranslationFactor = 20;
    private final double mRotationFactor = 0.07;

    private final int zoomBorder = 10;

    private Rectangle oldRect = null;

    private int mMousePressedX = 0;
    private int mMousePressedY = 0;
    private int mMouseReleasedX = 0;
    private int mMouseReleasedY = 0;
    private int mMouseDraggedX = 0;
    private int mMouseDraggedY = 0;

    private ArrayList<Point> mPoints;
    private boolean mCtrlPressed;
    private Toolkit mToolkit;
    private Clipboard mCb;

    /**
     * Constructor initializing callback to model.
     *
     * @param _model Model
     */
    public GISController(GISModel _model) {
        mModel = _model;
        mPoints = new ArrayList<Point>();
        mCtrlPressed = false;
        mToolkit = Toolkit.getDefaultToolkit();
        mCb = mToolkit.getSystemClipboard();
    }

    /**
     * Sets the member-view to the passed view.
     *
     * @param _view Passed view.
     */
    public void setView(GISView _view) {
        mView = _view;
    }

    /**
     * Performed action on window close.
     *
     * @param _e WindowEvent
     */
    @Override
    public void windowClosing(WindowEvent _e) {
        super.windowClosing(_e);
        System.exit(0);
    }

    /**
     * Performed action on draw-Button click.
     *
     * @param _e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent _e) {

        switch (_e.getActionCommand()) {
            case "Load":
                mModel.resetModel();
                mModel.loadData();
                break;
            case "ZTF":
                mModel.zoomToFit();
                System.out.println(">> ZoomToFit pressed");
                break;
            case "+":
                mModel.zoom(mZoomFactor);
                System.out.println(">> ZoomIn pressed");
                break;
            case "-":
                mModel.zoom(1 / mZoomFactor);
                System.out.println(">> ZoomOut pressed");
                break;
            case "Up":
                mModel.scrollVertical(-mTranslationFactor);
                System.out.println(">> ScrollUp pressed");
                break;
            case "Down":
                mModel.scrollVertical(+mTranslationFactor);
                System.out.println(">> ScrollDown pressed");
                break;
            case "Left":
                mModel.scrollHorizontal(-mTranslationFactor);
                System.out.println(">> ScrollLeft pressed");
                break;
            case "Right":
                mModel.scrollHorizontal(mTranslationFactor);
                System.out.println(">> ScrollRight pressed");
                break;
            case "Rot<":
                mModel.rotate(-mRotationFactor);
                System.out.println(">> RotateLeft pressed");
                break;
            case "Rot>":
                mModel.rotate(mRotationFactor);
                System.out.println(">> RotateRight pressed");
                break;
            case "POI":
                if (mModel.isPOIVisible()) {
                    mModel.setPOIVisible(false);
                } else {
                    mModel.setPOIVisible(true);
                }
                System.out.println(">> POI toggle pressed");
                break;
            case "Sticky":
                System.out.println(">> Sticky toggle pressed");
//			if (mModel.isStickyToggled()) {
//				((Button)_e.getSource()).setBackground(Color.WHITE);
//			} else {
//				((Button)_e.getSource()).setBackground(null);
//			}
                break;
            case "Store":
                System.out.println(">> Store image pressed");
                mModel.saveImage();
                break;
            default:
                System.out.println(">> Scale Changed");
                long newScale = 0;
                try {
                    newScale = Long.parseLong(_e.getActionCommand());
                } catch (Exception _exp) {
                    if (_e.getActionCommand().substring(0, 2).equals("1:"))
                        newScale = Long.parseLong(_e.getActionCommand().substring(2, _e.getActionCommand().length()));
                }
                if (newScale != 0)
                    mModel.zoom((double) getScale() / newScale);
        }

        // Redraw polygons after modification
        if (!mModel.objectsEmpty()) {
            mModel.drawPolygons();
        }
    }

    /**
     * Performed action on window resizing.
     *
     * @param _e
     */
    @Override
    public void componentResized(ComponentEvent _e) {
        mModel.setSize(_e.getComponent().getWidth(), _e.getComponent().getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent _e) {
    }

    @Override
    public void componentHidden(ComponentEvent _e) {
    }

    /**
     * Performed action on mouse clicked.
     *
     * @param _e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent _e) {
        Vector<GeoObject> objs = new Vector<GeoObject>();
        if (_e.getClickCount() == 2) {
            objs = mModel.initSelection(mModel.getMapPoint(_e.getPoint()));

            // print to console
            System.out.println(">> Clicked object IDs: ");
            for (int i = 0; i < objs.size(); i++) {
                System.out.println(objs.get(i).getID());
            }
        }

        Point worldPt = mModel.getWorldCoordinates(_e.getPoint());
        if (mCtrlPressed) {
            if (mPoints == null)
                mPoints = new ArrayList<Point>();
            mPoints.add(worldPt);
        } else {
            mCb.setContents(new StringSelection(worldPt.x + ", " + worldPt.y + "\n"), new StringSelection("UE03"));
        }
    }

    /**
     * Performed action on pressed mouse button
     *
     * @param _e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent _e) {
        mMousePressedX = _e.getX();
        mMousePressedY = _e.getY();
        mMouseDraggedX = mMousePressedX;
        mMouseDraggedY = mMousePressedY;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
    }

    @Override
    public void mouseExited(MouseEvent _e) {
    }

    /**
     * Performed action on released mouse button.
     *
     * @param _e MouseEvent
     */
    @SuppressWarnings("static-access")
    @Override
    public void mouseReleased(MouseEvent _e) {
        mMouseReleasedX = _e.getX();
        mMouseReleasedY = _e.getY();

        if (_e.getButton() == _e.BUTTON1) {

            int width = Math.abs(mMousePressedX - mMouseReleasedX);
            int height = Math.abs(mMousePressedY - mMouseReleasedY);
            int x = 0;
            int y = 0;

            if (mMousePressedX < mMouseReleasedX)
                x = mMousePressedX;
            else
                x = mMouseReleasedX;

            if (mMousePressedY < mMouseReleasedY)
                y = mMousePressedY;
            else
                y = mMouseReleasedY;

            if (width > zoomBorder && height > zoomBorder)
                mModel.zoomRect(new Rectangle(x, y, width, height));
        }

        mView.mPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        oldRect = null;
        mModel.drawPolygons();
    }

    /**
     * Performed action on mouse wheel moved.
     *
     * @param _e MouseEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent _e) {
        if (_e.getWheelRotation() < 0) {
            // Scroll up
            mModel.zoom(mZoomFactor);
        } else {
            // Scroll down
            mModel.zoom(1 / mZoomFactor);
        }
        mModel.drawPolygons();
    }

    /**
     * Returns the actual scale.
     *
     * @return Scale
     */
    public long getScale() {
        return mModel.getScale();
    }

    /**
     * Action performed on mouse dragged event.
     *
     * @param _e MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent _e) {
        if (SwingUtilities.isLeftMouseButton(_e)) {
            mView.mPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (oldRect != null) {
                mView.drawRect(oldRect);
            }
            oldRect = new Rectangle(new Point(mMousePressedX, mMousePressedY));
            oldRect.add(_e.getPoint());
            mView.drawRect(oldRect);
        } else {
            mView.mPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            int deltaX = _e.getX() - mMouseDraggedX;
            int deltaY = _e.getY() - mMouseDraggedY;

            mModel.scrollHorizontal(deltaX);
            mModel.scrollVertical(deltaY);

            mView.scrollRect(deltaX, deltaY);

            mMouseDraggedX = _e.getX();
            mMouseDraggedY = _e.getY();

        }
    }

    @Override
    public void mouseMoved(MouseEvent _e) {
        // TODO Auto-generated method stub

    }

    /**
     * Eventhandler for keyboard inputs.
     */
    @Override
    public void keyPressed(KeyEvent _arg0) {
        if (_arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
            mCtrlPressed = true;
            mView.mPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    /**
     * Eventhandler for keyboard inputs.
     */
    @Override
    public void keyReleased(KeyEvent _arg0) {
        if (_arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
            mView.mPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            mCtrlPressed = false;
            if (mPoints != null) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < mPoints.size(); i++) {
                    sb.append(mPoints.get(i).x + ", " + mPoints.get(i).y + "\n");
                }
                mCb.setContents(new StringSelection(sb.toString()), new StringSelection("UE03"));
                mPoints.clear();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent _arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * Eventhandler for Choice box.
     */
    @Override
    public void itemStateChanged(ItemEvent _e) {
        if (_e.getItem() == "OSM") {
            mModel.useOSM(true);
            System.out.println(">> Selected OSM");
        } else {
            mModel.useOSM(false);
            System.out.println(">> Selected DummyGIS");
        }
    }

}
