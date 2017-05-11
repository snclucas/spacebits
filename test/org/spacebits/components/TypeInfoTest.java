package org.spacebits.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.RadioCommunicator;
import org.spacebits.components.comms.SubSpaceCommunicator;
import org.spacebits.components.computers.BasicSystemComputer;
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
		assertEquals("", new TypeInfo("CommunicationDevice"), RadioCommunicator.categoryID);
		assertEquals("", new TypeInfo("CommunicationDevice"), SubSpaceCommunicator.categoryID);
		
		
		assertEquals("", new TypeInfo("Computer"), BasicSystemComputer.category);
		
		
		assertEquals("", new TypeInfo("CelestialObject"), Star.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Planet.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Region.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), Asteroid.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), UnknownObject.categoryID);
		assertEquals("", new TypeInfo("CelestialObject"), SubspaceBeacon.categoryID);
		
		
		
		assertEquals("", "SubspaceBeacon", SubspaceBeacon.typeInfo.toString());
		
		
		assertEquals("", "RadioCommunicator", RadioCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		
		assertEquals("", "BasicSystemComputer", BasicSystemComputer.type.typeIdString);
		
		assertEquals("", "SimpleSolarArray", SimpleSolarArray.typeID.typeIdString);
		
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.typeID.typeIdString);
		
	}

}
