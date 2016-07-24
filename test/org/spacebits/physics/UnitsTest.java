package org.spacebits.physics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacebits.physics.Unit;

public class UnitsTest {
	
	
	@Test
	public void testUnitConversion() {
		
		assertEquals("Unit conversion km not correct", 1000.0, 1 * Unit.Km, 0.001);
		assertEquals("Unit conversion cm not correct", 0.01, 1 * Unit.cm, 0.001);
		
		assertEquals("Unit conversion Ly not correct", 9460730472580800.0 * Unit.m, 1 * Unit.Ly, 0.001);
		assertEquals("Unit conversion Pc not correct", 3.2615679661840633266036314297735 * Unit.Ly, 1 * Unit.Pc, 0.001);
		
	}
	
	
	
	

}
