package org.spacebits.universe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Location;
import org.spacebits.universe.NavigationVector;
import org.spacebits.universe.SimpleLocation;
import org.spacebits.utils.Utils;

public class SimpleLocationTest {

	@Test
	public void testLocationConstructors() {	

		Coordinates coordinates1 = new Coordinates(new BigDecimal[]{new BigDecimal(1), new BigDecimal(1),new BigDecimal(1)});
		Coordinates coordinates3 = new Coordinates(new BigDecimal[]{new BigDecimal(1), new BigDecimal(0),new BigDecimal(1)});
		
		SimpleLocation simpleLocation1 =  new SimpleLocation(2, "Simple test location 1", coordinates1);
		SimpleLocation simpleLocation2 =  new SimpleLocation(3, "Simple test location 2", new BigDecimal[]{new BigDecimal(1), new BigDecimal(1),new BigDecimal(1)});
		SimpleLocation simpleLocation3 =  new SimpleLocation(3, "Simple test location 3", coordinates3);
		
		assertEquals("SimpleLocation category incorrect", Location.categoryID, simpleLocation1.getCategoryId());
		assertEquals("SimpleLocation type incorrect", SimpleLocation.typeID, simpleLocation1.getTypeId());
		
		assertEquals("Location 1 not equal to location 2", simpleLocation1, simpleLocation2);
		assertNotEquals("Location 1 equal to location 3", simpleLocation1, simpleLocation3);
		
		assertEquals("Distance from location 1 to location 1 should be 0", 0.0, Utils.distanceToLocation(simpleLocation1.getCoordinates(), simpleLocation1.getCoordinates()).doubleValue(), 0.0001);
		assertEquals("Distance from location 1 to location 3 should be 0", 1.0, Utils.distanceToLocation(simpleLocation1.getCoordinates(), simpleLocation3.getCoordinates()).doubleValue(), 0.0001);
		
		
		NavigationVector navVec1 = new NavigationVector(new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(0.0));
		NavigationVector navVec2 = new NavigationVector(new BigDecimal(0.0), new BigDecimal(-1.0), new BigDecimal(0.0));
		
		assertEquals("Navigation vector from location 1 to location 1 should be 0,0,0", navVec1, Utils.vectorToLocation(simpleLocation1.getCoordinates(), simpleLocation2.getCoordinates(), true));
		assertEquals("Normalized navigation vector from location 1 to location 3 should be 0,-1,0", navVec2, Utils.vectorToLocation(simpleLocation1.getCoordinates(), simpleLocation3.getCoordinates(), true));
		assertEquals("Un-normalized navigation vector from location 1 to location 3 should be 0,-1,0", navVec2, Utils.vectorToLocation(simpleLocation1.getCoordinates(), simpleLocation3.getCoordinates(), false));
	}

}
