package org.spacebits.spacecraft;

import org.spacebits.components.TypeInfo;

public class LocalBus extends AbstractBus {
	
	public static TypeInfo typeID = new TypeInfo("LocalBus");

	public LocalBus(String name) {
		super(name);
	}

	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}

}
