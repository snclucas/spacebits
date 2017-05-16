package org.spacebits.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.RadioCommunicator;
import org.spacebits.components.comms.SubSpaceCommunicator;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.Computer;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.energygeneration.SimpleSolarArray;
import org.spacebits.universe.celestialobjects.Asteroid;
import org.spacebits.universe.celestialobjects.Planet;
import org.spacebits.universe.celestialobjects.Region;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.universe.celestialobjects.UnknownObject;
import org.spacebits.universe.structures.SubspaceBeacon;

public class TypeInfoTest {

	@Test
	public void testTypeInfoInAllComponents() {
		// Test categories
		assertEquals("", new TypeInfo("CommunicationDevice"), RadioCommunicator.category());
		assertEquals("", new TypeInfo("CommunicationDevice"), SubSpaceCommunicator.category());
		
		
		assertEquals("", new TypeInfo("Computer"), Computer.category());
		assertEquals("", new TypeInfo("SystemComputer"), SystemComputer.category());
		
		assertEquals("", new TypeInfo("CelestialObject"), Star.category());
		assertEquals("", new TypeInfo("CelestialObject"), Planet.category());
		assertEquals("", new TypeInfo("CelestialObject"), Region.category());
		assertEquals("", new TypeInfo("CelestialObject"), Asteroid.category());
		assertEquals("", new TypeInfo("CelestialObject"), UnknownObject.category());
		assertEquals("", new TypeInfo("CelestialObject"), SubspaceBeacon.category());
		
		
		
		assertEquals("", "SubspaceBeacon", SubspaceBeacon.typeInfo.toString());
		
		
		assertEquals("", "RadioCommunicator", RadioCommunicator.type().toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type().toString());
		
		assertEquals("", "BasicSystemComputer", BasicSystemComputer.type().toString());
		
		assertEquals("", "SimpleSolarArray", SimpleSolarArray.type().toString());
		
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type().toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type().toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type().toString());
		
	}

}
