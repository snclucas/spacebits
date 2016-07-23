package org.braincycles.spacebits.universe;

import java.math.BigDecimal;

import org.braincycles.spacebits.components.TypeInfo;

public class SimpleLocation extends AbstractLocation {
	
	public static TypeInfo typeID = new TypeInfo("SimpleLocation");

	public SimpleLocation(int id, String name, Coordinates coordinates) {
		super(id, name, coordinates );
	}
	
	public SimpleLocation(int id, String name, BigDecimal ... coordComponents) {
		this(id, name, new Coordinates(coordComponents));
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	


}
