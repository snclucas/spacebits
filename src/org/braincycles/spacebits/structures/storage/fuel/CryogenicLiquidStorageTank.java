package org.braincycles.spacebits.structures.storage.fuel;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public class CryogenicLiquidStorageTank extends LiquidStorageTank {
	
	public static TypeInfo typeID = new TypeInfo("CryogenicLiquidStorageTank");

	public CryogenicLiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	@Override
	public String describe() {
		return "Cryogenic liquid storage tank, suitable for gases liquefied at cryogenic temperatures.";
	}
	
	
}
