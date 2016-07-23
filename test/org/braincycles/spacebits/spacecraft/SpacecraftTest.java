package org.braincycles.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.braincycles.spacebits.components.computers.BasicSystemComputer;
import org.braincycles.spacebits.components.computers.ComputerFactory;
import org.braincycles.spacebits.components.computers.SystemComputer;
import org.braincycles.spacebits.status.SystemStatus;
import org.braincycles.spacebits.structures.hulls.Hull;
import org.braincycles.spacebits.structures.hulls.HullFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
