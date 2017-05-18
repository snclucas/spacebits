package org.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;

public class SimpleSolarArrayTest {
	
	/*  */
	@Test
	public void testSimpleSolarArray() {
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(SimpleSolarArray.type());
		
		double arrayArea = 1 * Unit.m.value() * 10 * Unit.m.value();
		double efficiency = 75 * Unit.percent.value();
		
		PowerGenerator simpleSolarArray = new SimpleSolarArray("Test simple solar aarray", 
				data.getBusComponentSpecification(), arrayArea, efficiency);
	//	assertEquals("Array area of simple solar array incorrect", arrayArea, simpleSolarArray.get, 0.001);
		
		
		assertEquals("SimpleSolarArray category incorrect", SimpleSolarArray.category(), simpleSolarArray.getCategory());	
		assertEquals("SimpleSolarArray type incorrect", SimpleSolarArray.type(), simpleSolarArray.getType());
		
		
		
	}

}
