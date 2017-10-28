package at.fhooe.mcm.GIS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import at.fhooe.mcm.interfaces.IDataObserver;

public class GISModel {

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	public int width;
	public int height;

	IDataObserver mObserver = null;

	Image mCanvas = null;
	Vector<GeoObject> mData = null;
	ArrayList<Polygon> mDataList = null;
	GeoTransformationMatrix mMatrix = null;

	public GISModel() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		mCanvas = initCanvas(width, height);
		mDataList = new ArrayList<>();
	}

	public void resize(Dimension size) {
		width = (int) size.getWidth();
		height = (int) size.getHeight();
		mCanvas = initCanvas(width, height);
	}

	public void addMapObserver(IDataObserver observer) {
		mObserver = observer;
	}

	public void repaint() {
		if (mMatrix != null) {
			Graphics g = mCanvas.getGraphics();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.RED);
			for (GeoObject obj : mData) {
				DrawingContext.drawObject(obj, g, mMatrix);
			}
			update(mCanvas);
		} else {
			System.out.println("ERROR: Matrix not initialized yet!");
		}
	}

	protected Image initCanvas(int width, int height) {
		Image i = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = i.getGraphics();
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);
		return i;
	}

	protected void loadData() {
		mCanvas = initCanvas(width, height);
		OSMGeoServer server = new OSMGeoServer();
		server.login(OSMGeoServer.USER, OSMGeoServer.PASS, OSMGeoServer.CONN);
		mData = new Vector<GeoObject>(server.getData(OSMGeoServer.mQueries));
	}

	protected void update(Image _img) {
		mObserver.update(_img);
	}

	protected void zoomToFit() {

		if (mData != null) {
			Rectangle map = getMapBounds(mData);
			int w = mCanvas.getWidth(null);
			int h = mCanvas.getHeight(null);
			Rectangle win = new Rectangle(0, 0, w, h); // calculate the bounds
														// of the drawing canvas
			mMatrix = GeoTransformationMatrix.zoomToFit(map, win);
		} else {
			System.out.println("ERROR: No data yet!");
		}
	}

	protected void zoom(double _factor) {

		if (mMatrix != null) {
			int centerX = mCanvas.getWidth(null) / 2;
			int centerY = mCanvas.getHeight(null) / 2;
			try {
				mMatrix = GeoTransformationMatrix.zoomToPoint(mMatrix, new Point(centerX, centerY), _factor);
			} catch (NullPointerException e) {
				System.out.println("ERROR: Matrix not initialized yet!");
			}
		}
	}

	protected void drag(int _dx, int _dy) {

		if (mMatrix != null) {
			mMatrix = GeoTransformationMatrix.drag(mMatrix, _dx, _dy);
		}
	}

	protected Rectangle getMapBounds(Vector<GeoObject> _data) {
		Rectangle rect = _data.get(0).getBounds();
		for (int i = 1; i < _data.size(); i++) {
			rect = rect.union(_data.get(i).getBounds());
		}
		return rect;
	}
}
