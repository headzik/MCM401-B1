package at.fhooe.mcm.contextelements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeContext extends ContextElement {
	
	public enum TimeType {
		H24, AM, PM
	}

	private TimeType mType;
	private int mHours, mMinutes;
	
	public TimeContext(int _id, String _key, TimeType _type, int _h, int _min) {
		super(_id, _key);
		mType = _type;
		mHours = _h;
		mMinutes = _min;
	}
	
	public TimeType getType() {
		return mType;
	}
	
	public Date getTime() {
		String time = time = mHours + ":" + mMinutes + ":00";;
		DateFormat sdf;	
		try {
			switch (mType) {
			case H24:
				sdf = new SimpleDateFormat("HH:mm:ss");
				return sdf.parse(time);
			case AM:
				sdf = new SimpleDateFormat("hh:mm:ss");
				return sdf.parse(time);
			case PM: 
				sdf = new SimpleDateFormat("hh:mm:ss a");
				return sdf.parse(time);
				default:
					return null;
			}
		} catch (Exception _e) {
			return null;
		}
	}

}
