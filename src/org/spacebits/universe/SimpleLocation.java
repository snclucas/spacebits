package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.components.TypeInfo;

public class SimpleLocation extends AbstractLocation {
	
	public static TypeInfo typeID = new TypeInfo("SimpleLocation");

	public SimpleLocation(String name, Coordinates coordinates) {
		super(name, coordinates );
	}
	
	public SimpleLocation(String name, Coordinates coordinates, Location relativeTo) {
		super(name, coordinates, relativeTo);
	}
	
	public SimpleLocation(String name, BigDecimal ... coordComponents) {
		this(name, new Coordinates(coordComponents));
	}
	
	
	@Override
	public TypeInfo getType() {
		return typeID;
	}

	@Override
	public String describe() {
		return "Location";
	}

	@Override
	public String toString() {
		return name + ": ["+coordinates.toString() + "]";
	}
	
	
	
}
