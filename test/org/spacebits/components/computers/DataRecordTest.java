package org.spacebits.components.computers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.components.computers.DataRecord;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;

public class DataRecordTest {
	
	@Test
	public void testDataRecord() {
		
		Star sol = new Star(1,"Sol", new Coordinates(),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.G_CLASS_STAR));
		
		DataRecord record = new DataRecord("sol", sol);
		assertEquals("Data record ID not correct", "sol", record.getRecordTag());
		assertEquals("Data record type not equal to its archivable data", 
				sol.getCategoryId().typeId, record.getRecordType().typeId, 0.0001);
			
		assertEquals("Celestial object not found in data record", true, (record.getData() instanceof CelestialObject ) );
	}
	
}
