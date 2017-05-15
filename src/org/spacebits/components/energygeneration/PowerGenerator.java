package org.spacebits.components.energygeneration;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent {
	
	TypeInfo categoryID = new TypeInfo("PowerGenerator");
	
	public double getMaximumPowerOutput();

}
