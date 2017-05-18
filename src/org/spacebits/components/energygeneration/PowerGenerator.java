package org.spacebits.components.energygeneration;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent {
	
	public static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	public double getPowerOutput();
	public double getMaximumPowerOutput();

}
