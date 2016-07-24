package org.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;

public class BusRequirementSpecificationTest {

	@Test
	public void testBusRequirementSpecification() {
		
		double mass = 1.5 * Unit.kg;
		double volume = 4.5 * Unit.m3;		
		double height = 1.5 * Unit.m;
		double width = 4.5 * Unit.m;
		double length = 1.5 * Unit.m;
		
		PhysicalSpecification physicalSpecification = new PhysicalSpecification(mass, volume, length, width, height);
		
		
		double nominalPower = 1.5 * Unit.W;
		double nominalCPU = 4.5 * Unit.MFLOP;		
		double maxPower = 1.5 * Unit.W;
		double maxCPU = 4.5 * Unit.MFLOP;
		
		OperationalSpecification operationalSpecification = 
				new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU);
		
		
		BusComponentSpecification busComponentSpecification = 
				new BusComponentSpecification(physicalSpecification, operationalSpecification);
		
		
		assertEquals("Mass from physicalSpecification incorrect", mass, busComponentSpecification.getMass(), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, busComponentSpecification.getVolume(), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, busComponentSpecification.getLength(), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, busComponentSpecification.getHeight(), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, busComponentSpecification.getWidth(), 0.001);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, busComponentSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, busComponentSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, busComponentSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, busComponentSpecification.getMaximumOperationalCPUThroughput(), 0.001);
				
		
		mass = 10.5 * Unit.kg;
		volume = 40.5 * Unit.m3;
		height = 10.5 * Unit.m;
		width = 40.5 * Unit.m;
		length = 10.5 * Unit.m;
		
		busComponentSpecification.setMass(mass);
		busComponentSpecification.setVolume(volume);
		busComponentSpecification.setHeight(height);
		busComponentSpecification.setWidth(width);
		busComponentSpecification.setLength(length);
		
		
		nominalPower = 10.5 * Unit.W;
		nominalCPU = 40.5 * Unit.MFLOP;
		maxPower = 10.5 * Unit.W;
		maxCPU = 40.5 * Unit.MFLOP;
		
		busComponentSpecification.setNominalPower(nominalPower);
		busComponentSpecification.setNominalCPUThroughout(nominalCPU);
		busComponentSpecification.setMaximumOperationalPower(maxPower);
		busComponentSpecification.setMaximumOperationalCPUThroughput(maxCPU);
		
		
		assertEquals("Mass from physicalSpecification incorrect", mass, busComponentSpecification.getMass(), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, busComponentSpecification.getVolume(), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, busComponentSpecification.getLength(), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, busComponentSpecification.getHeight(), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, busComponentSpecification.getWidth(), 0.001);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, busComponentSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, busComponentSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, busComponentSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, busComponentSpecification.getMaximumOperationalCPUThroughput(), 0.001);
	
		
		
	}
	

}
