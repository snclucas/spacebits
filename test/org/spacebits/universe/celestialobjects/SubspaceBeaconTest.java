package org.spacebits.universe.celestialobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacebits.physics.Unit;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.structures.SubspaceBeacon;

public class SubspaceBeaconTest {
	
	
	
	@Test
	public void testDataRecord() {

		// Beacon coordinates
		BigDecimal b1x =  new BigDecimal(10 * Unit.Pc); BigDecimal b1y =  new BigDecimal(50 * Unit.Pc); BigDecimal b1z =  new BigDecimal(0 * Unit.Pc); 
		
		SensorSignalResponseProfile sensorSignalResponseProfile = SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON);
		SubspaceBeacon subspaceBeacon1 = new SubspaceBeacon("Test beacon 1", new Coordinates(b1x, b1y, b1z), sensorSignalResponseProfile);
		
		assertEquals("Subspace beacon type incorrect", SubspaceBeacon.typeInfo, subspaceBeacon1.getTypeId());
				
		subspaceBeacon1.getSensorSignalResponse();
		subspaceBeacon1.getSensorSignalResponseProfile();

		
	}
	

}
