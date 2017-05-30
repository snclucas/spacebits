package org.spacebits.components.computers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;

public class DataStoreTestSuite {
	
	@Test
	public void testDataRecord() {
		
		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		
		DataRecord record = new DataRecord("sol", sol);
		assertEquals("Data record ID not correct", "sol", record.getRecordID());
		assertEquals("Data record type not equal to its archivable data", 
				sol.getCategory(), record.getRecordType());
			
		assertEquals("Celestial object not found in data record", true, (record.getData() instanceof CelestialObject ) );
	}
	
}
