package org.braincycles.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.junit.Test;

public class SubspacePowerGeneratorTest {
	
	/*  */
	@Test
	public void testSubEtherPowerGenerator() {
		
		double mass = 25.0;
		double volume = 1.0;
		double nominalPower = 100 * Unit.W; 
		double nominalCPUThroughput = 10 * Unit.kFLOP;
		double maxOutputPower = 100 * Unit.kW;
		
		BusComponentSpecification busSpecs = new BusComponentSpecification(
				new PhysicalSpecification(mass, volume),
				new OperationalSpecification( nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));
		
		SubspacePowerExtractor subspacePowerGenerator = new SubspacePowerExtractor("Test sub-ether generator", busSpecs, maxOutputPower);
		assertEquals("Max output power of sunether generator incorrect", maxOutputPower, subspacePowerGenerator.getMaximumPowerOutput(), 0.001);
		
		
		assertEquals("SubspacePowerExtractor category incorrect", SubspacePowerExtractor.categoryID, subspacePowerGenerator.getCategoryId());	
		assertEquals("SubspacePowerExtractor type incorrect", SubspacePowerExtractor.typeID, subspacePowerGenerator.getTypeId());
		
		
		
	}

}
