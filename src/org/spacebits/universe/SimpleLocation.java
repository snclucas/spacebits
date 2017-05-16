package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.components.TypeInfo;

public class SimpleLocation extends AbstractLocation {
	
	public static TypeInfo typeID = new TypeInfo("SimpleLocation");

	public SimpleLocation(int id, String name, Coordinates coordinates) {
		super(id, name, coordinates );
	}
	
	public SimpleLocation(int id, String name, Coordinates coordinates, Location relativeTo) {
		super(id, name, coordinates, relativeTo);
	}
	
	public SimpleLocation(int id, String name, BigDecimal ... coordComponents) {
		this(id, name, new Coordinates(coordComponents));
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}

	@Override
	public String describe() {
		return "Location";
	}
	
}
