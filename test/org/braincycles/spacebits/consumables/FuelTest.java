package org.braincycles.spacebits.consumables;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FuelTest {

		@Test
		public void testFuel() {
			Fuel fuel = new Fuel(123.23);
			assertEquals("Sensor type incorrect", 123.23, fuel.getDensity(), 0.0001);
		}

}
