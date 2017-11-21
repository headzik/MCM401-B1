package at.fhooe.mcm.context.parsers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;
import at.fhooe.mcm.context.elements.TimeContext;
import at.fhooe.mcm.interfaces.IContextParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomParser implements IContextParser {

	public DomParser() {

	}

	@Override
	public ContextElement parseContextFromXML(String _filePath) {
		ContextElement ce = null;
		String contextId = "";
		String contextKey = "";
		String valueType = "";
		String value = "";
		int contextIdInt = 0;

		try {
			File file = new File(_filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("contextElement");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				contextId = eElement.getElementsByTagName("contextId").item(0).getTextContent();
				contextKey = eElement.getElementsByTagName("contextKey").item(0).getTextContent();
				valueType = eElement.getElementsByTagName("valueType").item(0).getTextContent();
				value = eElement.getElementsByTagName("value").item(0).getTextContent();

				contextIdInt = Integer.parseInt(contextId);
			}

			switch (contextKey) {
				case "position": {
					PositionContext.PositionType type = PositionContext.PositionType.valueOf(valueType.toUpperCase());
					int commaindex = value.indexOf(",");
					int x = Integer.parseInt(value.substring(0, commaindex));
					int y = Integer.parseInt(value.substring(commaindex + 1, value.length()));
					ce = new PositionContext(contextIdInt, contextKey, type, x, y);
					break;
				}
				case "speed": {
					SpeedContext.SpeedType type = SpeedContext.SpeedType.valueOf(valueType.toUpperCase());
					int speed = Integer.parseInt(value);
					ce = new SpeedContext(contextIdInt, contextKey, type, speed);
					break;
				}
				case "temperature": {
					TemperatureContext.TemperatureType type = TemperatureContext.TemperatureType
							.valueOf(valueType.toUpperCase());
					int temperature = Integer.parseInt(value);
					ce = new TemperatureContext(contextIdInt, contextKey, type, temperature);
					break;
				}
				case "time": {
					TimeContext.TimeType type = TimeContext.TimeType.valueOf(valueType.toUpperCase());
					int commaindex = value.indexOf(",");
					int hh = Integer.parseInt(value.substring(0, commaindex));
					int mm = Integer.parseInt(value.substring(commaindex + 1, value.length()));
					ce = new TimeContext(contextIdInt, contextKey, type, hh, mm);
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
