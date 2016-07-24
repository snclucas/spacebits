package org.spacebits.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import org.spacebits.Configuration;
import org.spacebits.physics.Unit;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.NavigationVector;
import org.spacebits.utils.math.BigDecimalMath;
import org.spacebits.utils.math.MathUtils;

public class Utils {
	
	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);

	public static BigDecimal[] doubleArrayToBigDecimalArray(double[] doubleArray) {
		BigDecimal[] bigDecimalArray = new BigDecimal[doubleArray.length];
		for(int i = 0;i<doubleArray.length;i++)
			bigDecimalArray[i] = new BigDecimal(doubleArray[i], mc);
		return bigDecimalArray;
	}
	
	
	
	public static BigDecimal distanceToLocation(Coordinates coordinates1, Coordinates coordinates2)
	{
		BigDecimal dx = coordinates1.get(0).subtract(coordinates2.get(0));
		BigDecimal dy = coordinates1.get(1).subtract(coordinates2.get(1));
		BigDecimal dz = coordinates1.get(2).subtract(coordinates2.get(2));
		return MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
	}


	public static NavigationVector vectorToLocation(Coordinates coordinates1, Coordinates coordinates2, boolean normalized) {
		BigDecimal dx = coordinates2.get(0).subtract(coordinates1.get(0));
		BigDecimal dy = coordinates2.get(1).subtract(coordinates1.get(1));
		BigDecimal dz = coordinates2.get(2).subtract(coordinates1.get(2));
		
		BigDecimal length = MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
		
		if(normalized)
			return new NavigationVector(dx.divide(length, RoundingMode.HALF_UP), dy.divide(length, RoundingMode.HALF_UP), dz.divide(length, RoundingMode.HALF_UP));
		else 
			return new NavigationVector(dx, dy, dz);
	}
	
	
	
	public static Coordinates galacticCoordinatesToAbsoluteCoordinates(double rightAscension, double declination, BigDecimal distance) {
		BigDecimal xSol = new BigDecimal(0.0, mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal ySol = new BigDecimal(-8 * Unit.kPc, mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal zSol = new BigDecimal(100 * Unit.Ly, mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal RA = new BigDecimal(rightAscension).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		BigDecimal dec = new BigDecimal(declination).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimalMath.toRadians(RA);
		
		BigDecimal x = distance.multiply(BigDecimalMath.sin(BigDecimalMath.toRadians(RA))).add(xSol, mc);
		BigDecimal y = distance.multiply(BigDecimalMath.cos(BigDecimalMath.toRadians(RA))).add(ySol, mc);	
		BigDecimal z = distance.multiply(BigDecimalMath.sin(BigDecimalMath.toRadians(dec))).add(zSol, mc);
		return new Coordinates(x, y, z);
		
	}
	
	
	public static double hmsToDeg(double h, double m, double s) {
		int multplier = (h>=0)?1:-1;
		return multplier*(Math.abs(h) + (m/60.0) + (s/3600.0));
	}
	
	public static double RAToDeg(double h, double m, double s) {
		int multplier = (h>=0)?1:-1;
		return 15*multplier*(Math.abs(h) + (m/60.0) + (s/3600.0));
	}
	
	
	public static double[] mergeArray(double[] array1, double[] array2) {
		if(array1.length != array2.length)
			throw new InvalidParameterException();
		double[] mergedArray = new double[array1.length];
		for(int i = 0;i< array1.length;i++)
			mergedArray[i] = array1[i] + array2[i];
		return mergedArray;
	}
	
	

}
