package org.spacebits.universe;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.spacebits.components.TypeInfo;
import org.spacebits.utils.math.MathUtils;

public abstract class AbstractLocation implements Location  {
	
	protected String name;
	protected int id;
	protected Coordinates coordinates;
	
	
	public AbstractLocation(int id, String name, Coordinates coordinates) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
	}
	
	public AbstractLocation(int id, String name, BigDecimal[] coordComponents) {
		this(id, name, new Coordinates(coordComponents));
	}
	
	public AbstractLocation(int id, String name, BigDecimal coordComponents1, BigDecimal coordComponents2, BigDecimal coordComponents3) {
		this(id, name, new Coordinates(coordComponents1, coordComponents2, coordComponents3));
	}
	
	public AbstractLocation(int id, String name, double coordComponents1, double coordComponents2, double coordComponents3) {
		this(id, name, new Coordinates(new BigDecimal(coordComponents1), new BigDecimal(coordComponents2), new BigDecimal(coordComponents3)));
	}
	
	
	@Override
	public int getId() {
		return this.hashCode();
	}
	
	@Override
	public TypeInfo getCategoryId() {
		return categoryID;
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	
	public BigDecimal getCoordinate(int index) {
		return this.coordinates.get(index);
	}


	public BigDecimal adistanceToLocation(Location location)
	{
		BigDecimal dx = location.getCoordinates().get(0).subtract(this.getCoordinates().get(0));
		BigDecimal dy = location.getCoordinates().get(1).subtract(this.getCoordinates().get(1));
		BigDecimal dz = location.getCoordinates().get(2).subtract(this.getCoordinates().get(2));
		return MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
	}


	public NavigationVector avectorToLocation(Location location, boolean normalized) {
		BigDecimal dx = location.getCoordinates().get(0).subtract(this.getCoordinates().get(0));
		BigDecimal dy = location.getCoordinates().get(1).subtract(this.getCoordinates().get(1));
		BigDecimal dz = location.getCoordinates().get(2).subtract(this.getCoordinates().get(2));
		
		BigDecimal length = MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
		
		if(normalized)
			return new NavigationVector(dx.divide(length, RoundingMode.HALF_UP), dy.divide(length, RoundingMode.HALF_UP), dz.divide(length, RoundingMode.HALF_UP));
		else 
			return new NavigationVector(dx, dy, dz);
	}

	
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		AbstractLocation other = (AbstractLocation) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
	
	
	
	

}
