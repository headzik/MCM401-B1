package JUnitTests;


import static org.junit.Assert.*;

import org.junit.Test;

import at.fhooe.mcm.context.elements.ContextElement;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;
import at.fhooe.mcm.context.elements.TimeContext;
import at.fhooe.mcm.context.parsers.DomParser;

public class DomParserTest {

	@Test
	public void testPosition() {
		DomParser parser = new DomParser();
		ContextElement ce = parser.parseContextFromXML("contextelements/position.xml");
		assertNotNull(ce);
		
		System.out.println(ce.getID());
		System.out.println(ce.getKey());
		assertTrue(ce instanceof PositionContext);
		
		PositionContext pc = (PositionContext) ce;
		
		System.out.println(pc.getType());
		System.out.println(pc.getPosition());
	}
	
	@Test
	public void testSpeed() {
		DomParser parser = new DomParser();
		ContextElement ce = parser.parseContextFromXML("contextelements/speed.xml");
		assertNotNull(ce);
		
		System.out.println(ce.getID());
		System.out.println(ce.getKey());
		assertTrue(ce instanceof SpeedContext);
		
		SpeedContext sc = (SpeedContext) ce;
		
		System.out.println(sc.getType());
		System.out.println(sc.getSpeed());
	}
	
	@Test
	public void testTemperature() {
		DomParser parser = new DomParser();
		ContextElement ce = parser.parseContextFromXML("contextelements/temperature.xml");
		assertNotNull(ce);
		
		System.out.println(ce.getID());
		System.out.println(ce.getKey());
		assertTrue(ce instanceof TemperatureContext);
		
		TemperatureContext tpc = (TemperatureContext) ce;
		
		System.out.println(tpc.getType());
		System.out.println(tpc.getTemperature());
	}
	
	@Test
	public void testTime() {
		DomParser parser = new DomParser();
		ContextElement ce = parser.parseContextFromXML("contextelements/time.xml");
		assertNotNull(ce);
		
		System.out.println(ce.getID());
		System.out.println(ce.getKey());
		assertTrue(ce instanceof TimeContext);
		
		TimeContext tc = (TimeContext) ce;
		
		System.out.println(tc.getType());
		System.out.println(tc.getTime());
	}
}
