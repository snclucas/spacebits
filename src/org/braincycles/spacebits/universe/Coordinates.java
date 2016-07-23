package org.braincycles.spacebits.universe;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import org.braincycles.spacebits.Configuration;

public class Coordinates {
	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	public static BigDecimal[] NOT_KNOWN = new BigDecimal[]{new BigDecimal(-1), new BigDecimal(-1), new BigDecimal(-1)};
	
	private BigDecimal[] location;
	
	
	public Coordinates() {
		super();
		this.location = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
	}

	public Coordinates(BigDecimal ... coords) {
		super();
		this.location = coords;
	}
	

	public BigDecimal[] getCoordinates() {
		return location;
	}
	
	
	public BigDecimal get(int index) {
		return location[index];
	}
	

	public BigDecimal get(int index, double unit) {
		return location[index].divide(new BigDecimal(unit), mc);
	}
	
	
	public Coordinates set(Coordinates coordinates) {
		return new Coordinates( 
				this.location[0].add(coordinates.get(0)), 
				this.location[1].add(coordinates.get(1)), 
				this.location[2].add(coordinates.get(2)));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(location);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Coordinates other = (Coordinates) obj;
		if (!Arrays.equals(location, other.location))
			return false;
		return true;
	}
	
	
	
}
