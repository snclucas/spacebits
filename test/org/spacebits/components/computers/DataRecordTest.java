package org.spacebits.components.computers;

import java.util.List;

import org.junit.Test;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.CelestialObject;

public class DataRecordTest {
	
	@Test
	public void testArchiveFiltering() {
		
		DataStore dataStore = new BasicDataStorageUnit("Test store", new BusComponentSpecification());
		
		List<CelestialObject> objects = Universe.getInstance().getLocationsByCategory(CelestialObject.category());
		
		dataStore.saveData(objects);
		
		System.out.println(dataStore.getData(CelestialObject.category()).size());
		
	}
	
}
