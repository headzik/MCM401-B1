package at.fhooe.mcm.rules;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Rule evaluator parses rules from an XML
 * @author ifumi
 *
 */
public class RuleEvaluator {

	/**
	 * Parses rules from an rules.xml file.
	 * @param _filePath The path to the rules file.
	 * @return An arraylist containing all rules in containers.
	 */
	public static ArrayList<RuleContainer> parseRulesContainerFromXML(String _filePath) {
		String condition = "";
		String className = "";
		String method = "";
		
		String parameterTypes = "";
		String parameterValues = "";
		ArrayList<RuleContainer> ruleContainers = new ArrayList<>();
		
		try {			
			File file = new File(_filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList rules = doc.getElementsByTagName("rule");
			
			for (int i = 0; i < rules.getLength(); i++){ 
				parameterTypes = "";        
				parameterValues = "";       
				NodeList action = ((Element)rules.item(i)).getElementsByTagName("action");
				NodeList parameters = ((Element)action.item(0)).getElementsByTagName("parameter");
				
				Node ruleNode = rules.item(i);
	            if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) ruleNode;
	    			condition = eElement.getElementsByTagName("condition").item(0).getTextContent();
	            }
	            
				Node actionNode = action.item(0);
	            if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) actionNode;
	                className = eElement.getElementsByTagName("class").item(0).getTextContent();
	                method = eElement.getElementsByTagName("method").item(0).getTextContent();
	            }
	            
				for (int j = 0; j < parameters.getLength(); j++)  {
					Node parameterNode = parameters.item(j);
		            if (parameterNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element eElement = (Element) parameterNode;
		                parameterTypes += eElement.getElementsByTagName("type").item(0).getTextContent();
		                parameterValues += eElement.getElementsByTagName("value").item(0).getTextContent();
		                parameterTypes += " ";
		                parameterValues += " ";
		            }
				}
				String actionPhrase = className + "/" + method + "/" + parameterTypes + "/" + parameterValues;
				RuleContainer ruleContainer = new RuleContainer(condition, actionPhrase);
				ruleContainers.add(ruleContainer);
	        }			
		} catch (Exception _e) {
			_e.printStackTrace();
		}
				
		return ruleContainers;
	}
}
