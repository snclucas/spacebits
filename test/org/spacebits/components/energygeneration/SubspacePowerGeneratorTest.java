package org.spacebits.components.energygeneration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.components.energygeneration.SubspacePowerExtractor;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;

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