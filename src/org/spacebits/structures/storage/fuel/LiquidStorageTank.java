package org.spacebits.structures.storage.fuel;

import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public class LiquidStorageTank extends AbstractFuelStorageTank {
	
	public static TypeInfo typeID = new TypeInfo("LiquidStorageTank");

	public LiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	@Override
	public String describe() {
		return "Liquid storage tank, suitable for liquids and pressurised vapors.";
	}
	
	
}
