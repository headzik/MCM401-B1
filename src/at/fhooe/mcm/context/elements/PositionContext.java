package at.fhooe.mcm.context.elements;

import org.postgis.Point;

/**
 * Context element for position.
 * @author ifumi
 *
 */
public class PositionContext extends ContextElement {

	public enum PositionType {
		GAUSSKRUEGER, WGS84, UTM
	}
	
	public static final String KEY = "position";
	
	private int mXPos, mYPos;
	private PositionType mType;

	public PositionContext(int _id, String _key, PositionType _type, int _x, int _y) {
		super(_id, _key);
		mType = _type;
		mXPos = _x;
		mYPos = _y;
	}

	public PositionType getType() {
		return mType;
	}
	
	public Point getPosition() {
		return new Point (mXPos, mYPos);
	}

	@Override
	public String toString() {
		return mXPos+","+mYPos;
	}
}
