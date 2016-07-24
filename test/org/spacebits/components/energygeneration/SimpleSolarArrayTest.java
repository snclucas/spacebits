package org.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.components.energygeneration.PowerGenerator;
import org.spacebits.components.energygeneration.SimpleSolarArray;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;

public class SimpleSolarArrayTest {
	
	/*  */
	@Test
	public void testSimpleSolarArray() {
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(SimpleSolarArray.typeID.toString());
		
		double arrayArea = 1 * Unit.m * 10 * Unit.m;
		double efficiency = 75 * Unit.percent;
		
		PowerGenerator simpleSolarArray = new SimpleSolarArray("Test simple solar aarray", data.getBusComponentSpecification(), arrayArea, efficiency);
	//	assertEquals("Array area of simple solar array incorrect", arrayArea, simpleSolarArray.get, 0.001);
		
		
		assertEquals("SimpleSolarArray category incorrect", SimpleSolarArray.categoryID, simpleSolarArray.getCategoryId());	
		assertEquals("SimpleSolarArray type incorrect", SimpleSolarArray.typeID, simpleSolarArray.getTypeId());
		
		
		
	}

}
