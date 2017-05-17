package org.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class SpacecraftTest {
	
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", hull);

	@Test
	public void testSpacecraft() {
		assertEquals(Spacecraft.category, spacecraft.getCategory());
		// Should fail to online (Critical status: No system computer found! Aborting spacecraft onlining.)
		SystemStatus systemStatus = spacecraft.online();
		
		assertEquals(false, spacecraft.isOnline());
		assertEquals(false, systemStatus.isOK());
		assertEquals(true, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type());	
		spacecraft.addComponent(systemComputer);
		
		
		systemStatus = spacecraft.online();
		
		assertEquals(true, spacecraft.isOnline());
		assertEquals(true, systemStatus.isOK());
		assertEquals(false, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());
		
		
		System.out.println(spacecraft.getMass());
		
	
	}
	
	
	@Test
	public void testSpacecraftHullData() {
		
		//spacecraftBus.getE
		
	}
	
	
	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}
