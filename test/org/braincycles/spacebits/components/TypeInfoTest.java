package org.braincycles.spacebits.components;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.components.comms.RadioCommunicator;
import org.braincycles.spacebits.components.comms.SubSpaceCommunicator;
import org.braincycles.spacebits.components.computers.BasicSystemComputer;
import org.braincycles.spacebits.components.energygeneration.SimpleSolarArray;
import org.braincycles.spacebits.universe.celestialobjects.Asteroid;
import org.braincycles.spacebits.universe.celestialobjects.Planet;
import org.braincycles.spacebits.universe.celestialobjects.Region;
import org.braincycles.spacebits.universe.celestialobjects.Star;
import org.braincycles.spacebits.universe.celestialobjects.UnknownObject;
import org.braincycles.spacebits.universe.structures.SubspaceBeacon;
import org.junit.Test;

public class TypeInfoTest {

	@Test
	public void testTypeInfoInAllComponents() {
		// Test categories
		assertEquals("", new TypeInfo("CommunicationDevice"), RadioCommunicator.categoryID);
		assertEquals("", new TypeInfo("CommunicationDevice"), SubSpaceCommunicator.categoryID);
		
		
		assertEquals("", new TypeInfo("Computer"), BasicSystemComputer.categoryID);
		
		
		assertEquals("", new TypeInfo("CelestialObject"), Star.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Planet.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Region.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Asteroid.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), UnknownObject.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), SubspaceBeacon.categoryID);
		
		
		
		assertEquals("", "SubspaceBeacon", SubspaceBeacon.typeInfo.toString());
		
		
		assertEquals("", "RadioCommunicator", RadioCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		
		assertEquals("", "BasicSystemComputer", BasicSystemComputer.typeID.typeIdString);
		
		assertEquals("", "SimpleSolarArray", SimpleSolarArray.typeID.typeIdString);
		
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		
	}

}
