package org.spacebits.components.energygeneration;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent, BusCommunicator {
	
	TypeInfo categoryID = new TypeInfo("PowerGenerator");
	
	public double getMaximumPowerOutput();

}
