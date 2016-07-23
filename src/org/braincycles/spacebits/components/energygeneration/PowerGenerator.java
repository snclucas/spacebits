package org.braincycles.spacebits.components.energygeneration;

import org.braincycles.spacebits.components.BusCommunicator;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent, BusCommunicator {
	
	TypeInfo categoryID = new TypeInfo("PowerGenerator");
	
	public double getMaximumPowerOutput();

}
