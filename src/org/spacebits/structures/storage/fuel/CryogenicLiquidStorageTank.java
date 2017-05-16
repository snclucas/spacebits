package org.spacebits.structures.storage.fuel;

import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public class CryogenicLiquidStorageTank extends LiquidStorageTank {
	
	public static TypeInfo type() {
		return new TypeInfo("CryogenicLiquidStorageTank");
	}

	public CryogenicLiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}
	
	
	@Override
	public String describe() {
		return "Cryogenic liquid storage tank, suitable for gases liquefied at cryogenic temperatures.";
	}
	
	
}
