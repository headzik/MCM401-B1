package JUnitTests;


import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.rules.RuleContainer;
import at.fhooe.mcm.rules.RuleEvaluator;

public class RuleContainerTest {

	@Test
	public void testExecute() {
		
		ArrayList<RuleContainer> ruleContainers = RuleEvaluator.parseRulesContainerFromXML("rules/rules.xml");
		
		for(RuleContainer ruleContainer: ruleContainers) {
			ruleContainer.execute();
		}
	}
	
	@Test
	public void testValidate() {
//		ArrayList<RuleContainer> ruleContainers = RuleEvaluator.parseRulesContainerFromXML("rules/rules.xml");
//		
//		for(RuleContainer ruleContainer: ruleContainers) {
//			ruleContainer.valid();
//		}
	}

}
