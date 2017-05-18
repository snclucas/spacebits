package org.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.universe.Universe;

public class SimpleSolarArrayTest {
	
	/*  */
	@Test
	public void testSimpleSolarArray() {
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(SimpleSolarArray.type());
		
		double arrayArea = 10.0* Unit.m2.value();
		double efficiency = 75 * Unit.percent.value();
		
		SimpleSolarArray simpleSolarArray = new SimpleSolarArray("Test simple solar aarray", 
				data.getBusComponentSpecification(), arrayArea, efficiency);
		assertEquals("Array area of simple solar array incorrect", arrayArea, simpleSolarArray.getArrayArea(), 0.001);
		assertEquals("Efficiency of simple solar array incorrect", efficiency, simpleSolarArray.getEfficiency(), 0.001);
		
		assertEquals("SimpleSolarArray category incorrect", SimpleSolarArray.category(), simpleSolarArray.getCategory());	
		assertEquals("SimpleSolarArray type incorrect", SimpleSolarArray.type(), simpleSolarArray.getType());
	}
	
	
	@Test
	public void testPowerGeneration() {
		Universe universe = Configuration.getUniverse();
		Spacecraft shuttle = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		universe.addSpacecraft(shuttle);
		
		//System.out.println(Universe.getUniverse().);
		
		
		
		
	}

}
