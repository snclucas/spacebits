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
		DataRecord record1 = new DataRecord();
		assertEquals("Data record is not empty.", true,record1.isEmpty());
		
		Star sol = new Star(1,"Sol", new Coordinates(),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.G_CLASS_STAR));
		
		DataRecord record2 = new DataRecord(10,sol);
		assertEquals("Data record is empty.", false,record2.isEmpty());
		assertEquals("Data record ID not correct", 10, record2.getRecordID(), 0.0001);
		assertEquals("Data record type not equal to its archivable data", sol.getCategoryId().typeId, record2.getRecordType().typeId, 0.0001);
		
		DataRecord record3 = sol.getDataRecord();
		assertEquals("Data record is empty.", false,record3.isEmpty());
		assertEquals("Data record ID not correct", sol.getId(), record3.getRecordID(), 0.0001);
		assertEquals("Data record type not equal to its archivable data", CelestialObject.categoryID.typeId, record3.getRecordType().typeId, 0.0001);
		
		assertEquals("Celestial object not found in data record", true, (record3.getData() instanceof CelestialObject ) );
	}
	
}
