package at.fhooe.mcm.context.elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
	
	public LocalTime getTime() {
		// Build hh:mm:ss string from time variables
		
		StringBuffer sb = new StringBuffer();
		if (mHours < 10)
			sb.append("0");
		sb.append(mHours);
		sb.append(":");
		if (mMinutes < 10)
			sb.append("0");
		sb.append(mMinutes);
		sb.append(":00");
		
		return LocalTime.parse(sb.toString());
		
		
		/*
		// Parse to Date object depending on the type of the time
		DateFormat sdf;	
		try {
			switch (mType) {
			case H24:
				sdf = new SimpleDateFormat("HH:mm:ss");
				return sdf.parse(sb.toString());
			case AM:
				sdf = new SimpleDateFormat("hh:mm:ss");
				return sdf.parse(sb.toString());
			case PM: 
				sdf = new SimpleDateFormat("hh:mm:ss a");
				return sdf.parse(sb.toString());
				default:
					return null;
			}
		} catch (Exception _e) {
			return null;
		}
		*/
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (mHours < 10)
			sb.append("0");
		sb.append(mHours);
		sb.append(":");
		if (mMinutes < 10)
			sb.append("0");
		sb.append(mMinutes);
		return sb.toString();
	}
}
