package org.spacebits.spacecraft;

import org.spacebits.components.TypeInfo;

public class SpacecraftBus extends AbstractBus {

	public static TypeInfo typeID = new TypeInfo("SpacecraftBus");
	
	public SpacecraftBus(String name) {
		super(name);
	}

	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	

}
