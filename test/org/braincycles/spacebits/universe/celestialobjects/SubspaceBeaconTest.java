package org.braincycles.spacebits.universe.celestialobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.universe.Coordinates;
import org.braincycles.spacebits.universe.structures.SubspaceBeacon;
import org.junit.Test;

public class SubspaceBeaconTest {
	
	
	
	@Test
	public void testDataRecord() {

		// Beacon coordinates
		BigDecimal b1x =  new BigDecimal(10 * Unit.Pc); BigDecimal b1y =  new BigDecimal(50 * Unit.Pc); BigDecimal b1z =  new BigDecimal(0 * Unit.Pc); 
		
		SensorSignalResponseProfile sensorSignalResponseProfile = SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON);
		SubspaceBeacon subspaceBeacon1 = new SubspaceBeacon(1, "Test beacon 1", new Coordinates(b1x, b1y, b1z), sensorSignalResponseProfile);
		
		assertEquals("Subspace beacon type incorrect", SubspaceBeacon.typeInfo, subspaceBeacon1.getTypeId());
				
		subspaceBeacon1.getSensorSignalResponse();
		subspaceBeacon1.getSensorSignalResponseProfile();

		
	}
	

}
