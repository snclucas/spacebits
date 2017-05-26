package org.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacebits.Configuration;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;

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
		Universe universe = Universe.getInstance();
		Spacecraft shuttle = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		Star sol = new Star("Sol", Star.G_CLASS_STAR, new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		Coordinates coords = sol.getCoordinates().add(
				new Coordinates(new BigDecimal(10*Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO));
		universe.addSpacecraft(shuttle, coords);
		
		
		
		
		
		
	}

}
