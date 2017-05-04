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
	Spacecraft spacecraftBus = new SimpleSpacecraft("Test spacecraft", hull);

	@Test
	public void testSpacecraft() {
		assertEquals(Spacecraft.categoryID, spacecraftBus.getCategoryId());
		
		SystemStatus systemStatus = spacecraftBus.online();
		
		assertEquals(false, spacecraftBus.isOnline());
		assertEquals(false, systemStatus.isOK());
		assertEquals(true, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.typeID.toString());	
		spacecraftBus.addComponent(systemComputer);
		
		
		systemStatus = spacecraftBus.online();
		
		assertEquals(true, spacecraftBus.isOnline());
		assertEquals(true, systemStatus.isOK());
		assertEquals(false, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());
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