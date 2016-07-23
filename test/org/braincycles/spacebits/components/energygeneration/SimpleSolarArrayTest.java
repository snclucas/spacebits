package org.braincycles.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.data.SpacecraftDataProvider;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.junit.Test;

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
