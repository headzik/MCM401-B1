package JUnitTests;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import at.fhooe.mcm.rules.RuleContainer;
import at.fhooe.mcm.rules.RuleEvaluator;

public class RuleEvaluatorTest {

	@Test
	public void testListNotEmpty() {
		ArrayList<RuleContainer> emptyArrayList = new ArrayList<>();
		ArrayList<RuleContainer> ruleContainer = RuleEvaluator.parseRulesContainerFromXML("rules/rules.xml");
		assertNotEquals(ruleContainer, emptyArrayList);
	}

}
