package at.fhooe.mcm.GIS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import at.fhooe.mcm.GIS.GISModel;

public class GISController implements MouseListener, ComponentListener,
								 ActionListener,MouseWheelListener, 
								 MouseMotionListener {

	GISModel mModel = null;
	
	public static final int DELTA = 20;
	
	int mouseX, mouseY;
	
	public GISController(GISModel model) {
		mModel = model;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		mModel.resize(e.getComponent().getSize());
		mModel.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//mModel.drag();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd) {
			case "load": {
				mModel.loadData();
			} break;
			case "ZTF": {
				mModel.zoomToFit();
			} break;
			case "-": {
				mModel.zoom(0.2);
			} break;
			case "+": {
				mModel.zoom(1/0.2);
			} break;
			case "N": {
				mModel.drag(0, -DELTA);
			} break;
			case "S": {
				mModel.drag(0, DELTA);
			} break;
			case "W": {
				mModel.drag(-DELTA, 0);
			} break;
			case "E": {
				mModel.drag(DELTA, 0);
			} break;
			default : System.out.println("Wrong command");
		}
		mModel.repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double scrollingFactor = e.getPreciseWheelRotation()*1.5;
		mModel.drag(mModel.width/2 - e.getX(), mModel.height/2 - e.getY());
		if(scrollingFactor < 0) {
			mModel.zoom(Math.abs(scrollingFactor));
		} else {
			mModel.zoom(1/scrollingFactor);
		}
		mModel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mModel.drag((e.getX() - mouseX),
				(e.getY() - mouseY));
		mModel.repaint();
		mouseX = e.getX();
		mouseY = e.getY();
		//copy area
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
