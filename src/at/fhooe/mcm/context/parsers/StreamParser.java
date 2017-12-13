package at.fhooe.mcm.context.parsers;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;
import at.fhooe.mcm.context.elements.TimeContext;
import at.fhooe.mcm.interfaces.IContextParser;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;

/**
 * A parser using STREAM.
 * @author ifumi
 *
 */
public class StreamParser implements IContextParser {

	/**
	 * Parses a context element from an xml file.
	 */
	@Override
	public ContextElement parseContextFromXML(String _filePath) {
		ContextElement ce = null;

		ArrayList<String> context = new ArrayList<>();
		boolean addContext = false;

		//String contextId="";
		int contextId=0;
		String contextKey="";
		String valueType="";
		String value="";

		try {
			File file = new File(_filePath);
				KXmlParser parser = new KXmlParser();
				parser.setInput(new FileReader(file));
				parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);

				int eventType = parser.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
						case XmlPullParser.START_DOCUMENT: {
							break;
						}
						case XmlPullParser.END_DOCUMENT: {
							break;
						}
						case XmlPullParser.START_TAG: {
							if (parser.getName().equals("contextId") || parser.getName().equals("contextKey")
									|| parser.getName().equals("valueType") || parser.getName().equals("value")) {
								addContext = true;
							}
							break;
						}
						case XmlPullParser.END_TAG: {
							break;
						}
						case XmlPullParser.TEXT: {
							if (addContext) {
								context.add(parser.getText());
								addContext = false;
							}
							break;
						}
					}
					eventType = parser.next();
				}
				
				contextId = Integer.parseInt(context.get(0));
				contextKey = context.get(1);
				valueType = context.get(2);
				value = context.get(3);
				
				switch (contextKey) {
				case "position": {
					PositionContext.PositionType type = PositionContext.PositionType.valueOf(valueType.toUpperCase());
					int commaindex = value.indexOf(",");
					int x = Integer.parseInt(value.substring(0, commaindex));
					int y = Integer.parseInt(value.substring(commaindex + 1, value.length()));
					ce = new PositionContext(contextId, contextKey, type, x, y);
					break;
				}
				case "speed": {
					SpeedContext.SpeedType type = SpeedContext.SpeedType.valueOf(valueType.toUpperCase());
					int speed = Integer.parseInt(value);
					ce = new SpeedContext(contextId, contextKey, type, speed);
					break;
				}
				case "temperature": {
					TemperatureContext.TemperatureType type = TemperatureContext.TemperatureType
							.valueOf(valueType.toUpperCase());
					int temperature = Integer.parseInt(value);
					ce = new TemperatureContext(contextId, contextKey, type, temperature);
					break;
				}
				case "time": {
					TimeContext.TimeType type = TimeContext.TimeType.valueOf(valueType.toUpperCase());
					int commaindex = value.indexOf(",");
					int hh = Integer.parseInt(value.substring(0, commaindex));
					int mm = Integer.parseInt(value.substring(commaindex + 1, value.length()));
					ce = new TimeContext(contextId, contextKey, type, hh, mm);
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ce;
	}
}
