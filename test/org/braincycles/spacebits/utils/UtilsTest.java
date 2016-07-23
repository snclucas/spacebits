package org.braincycles.spacebits.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.universe.Coordinates;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testCoordinateConversion() {

		//	17h 42.4m
		//−28.92°

		Coordinates coords = Utils.galacticCoordinatesToAbsoluteCoordinates(270, 10, new BigDecimal(1*Unit.kPc));
		System.out.println(coords.get(0, Unit.Pc).doubleValue() + " " + 
				coords.get(1, Unit.Pc).doubleValue() + " " + coords.get(2, Unit.Ly).doubleValue());



	}


	@Test
	public void testHMS2Deg() {

		//System.out.println(Utils.hmsToDeg(1,1,30));
		//System.out.println(Utils.RAToDeg(17,42,4));
	}


}
