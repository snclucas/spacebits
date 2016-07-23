package org.braincycles.spacebits.components.storage.energy;

import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.Executable;
import org.braincycles.spacebits.components.TypeInfo;

public interface EnergyStorageDevice extends SpacecraftBusComponent, Executable {
	
	TypeInfo categoryID = new TypeInfo("EnergyStorageDevice");
	
	double getStorageCapacity();
	
	double getChargeRate();
	
	double getDischargeRate();

}
