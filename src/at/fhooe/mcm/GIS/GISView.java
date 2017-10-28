package at.fhooe.mcm.GIS;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;

import at.fhooe.mcm.GIS.GISController;
import at.fhooe.mcm.interfaces.IDataObserver;

public class GISView implements IDataObserver {

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static int width;
	private static int height;
	
	private DrawingPanel panelView = null;
	
	public GISView(GISController controller) {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;

		panelView = new DrawingPanel();
		panelView.setLayout(new BorderLayout());
		panelView.setBackground(Color.LIGHT_GRAY);
		panelView.setPreferredSize(new Dimension(width, height));
		panelView.addComponentListener(controller);
		panelView.addMouseListener(controller);
		panelView.addMouseWheelListener(controller);
		panelView.addMouseMotionListener(controller);
				
		Button load = new Button("load");
		Button ztf = new Button("ZTF");
		Button p = new Button("+");
		Button m = new Button("-");
		Button N = new Button("N");
		Button S = new Button("S");
		Button W = new Button("W");
		Button E = new Button("E");
		load.addActionListener(controller);
		ztf.addActionListener(controller);
		p.addActionListener(controller);
		m.addActionListener(controller);
		N.addActionListener(controller);
		S.addActionListener(controller);
		W.addActionListener(controller);
		E.addActionListener(controller);
		
		Panel buttonBar = new Panel();
		buttonBar.setBackground(Color.WHITE);
		buttonBar.add(load);
		buttonBar.add(ztf);
		buttonBar.add(p);
		buttonBar.add(m);
		buttonBar.add(N);
		buttonBar.add(S);
		buttonBar.add(W);
		buttonBar.add(E);
		panelView.add(buttonBar, BorderLayout.SOUTH);
	}

	public Panel getPanelView() {
		return panelView;
	}
	
	@Override
	public void update(Image _img) {
		panelView.setImage(_img);
		panelView.repaint();
	}

}
