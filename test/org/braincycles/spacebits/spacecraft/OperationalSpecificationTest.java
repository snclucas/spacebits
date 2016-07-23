package org.braincycles.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.physics.Unit;
import org.junit.Test;

public class OperationalSpecificationTest {
	
	@Test
	public void testOperationalSpecification() {
		
		double nominalPower = 1.5 * Unit.W;
		double nominalCPU = 4.5 * Unit.MFLOP;		
		double maxPower = 1.5 * Unit.W;
		double maxCPU = 4.5 * Unit.MFLOP;
		
		OperationalSpecification operationalSpecification = 
				new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(), 0.001);
				
		nominalPower = 10.5 * Unit.W;
		nominalCPU = 40.5 * Unit.MFLOP;
		maxPower = 10.5 * Unit.W;
		maxCPU = 40.5 * Unit.MFLOP;
		
		operationalSpecification.setNominalPower(nominalPower);
		operationalSpecification.setNominalCPUThroughout(nominalCPU);
		operationalSpecification.setMaximumOperationalPower(maxPower);
		operationalSpecification.setMaximumOperationalCPUThroughput(maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(), 0.001);
		
	}

}
