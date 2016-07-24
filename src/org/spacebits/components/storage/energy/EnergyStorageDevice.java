package org.spacebits.components.storage.energy;

import org.spacebits.components.Executable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;

public interface EnergyStorageDevice extends SpacecraftBusComponent, Executable {
	
	TypeInfo categoryID = new TypeInfo("EnergyStorageDevice");
	
	double getStorageCapacity();
	
	double getChargeRate();
	
	double getDischargeRate();

}
