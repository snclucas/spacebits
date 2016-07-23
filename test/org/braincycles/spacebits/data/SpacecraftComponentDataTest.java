package org.braincycles.spacebits.data;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.junit.Test;

public class SpacecraftComponentDataTest {

	@Test
	public void testSpacecraftComponentData() {
		
		double mass = 1.5 * Unit.kg;
		double volume = 4.5 * Unit.m3;		
		double height = 1.5 * Unit.m;
		double width = 4.5 * Unit.m;
		double length = 1.5 * Unit.m;
		
		double nominalPower = 1.5 * Unit.W;
		double nominalCPU = 4.5 * Unit.MFLOP;		
		double maxPower = 1.5 * Unit.W;
		double maxCPU = 4.5 * Unit.MFLOP;
		
		double[] values = new double[]{100.34, 34.45};
		
		SpacecraftComponentData spacecraftComponentData = new SpacecraftComponentData(new BusComponentSpecification(
				new PhysicalSpecification(mass, volume, length, width, height), new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU)),  values);
		
		
		assertEquals("BusComponentSpecification incorrect", mass, spacecraftComponentData.getBusComponentSpecification().getMass(), 0.0001);
		
		assertEquals("Values incorrect", values, spacecraftComponentData.getValues());
		assertEquals("Value 2 incorrect", values[1], spacecraftComponentData.getValues(1), 0.0001);
		assertEquals("Size of values incorrect", values.length, spacecraftComponentData.size(), 0.0001);
	}

}
