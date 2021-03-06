package org.spacebits.components.shields;

import org.spacebits.components.Executable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusRequirement;

public interface Shield extends SpacecraftBusComponent, Executable {
	
	public static TypeInfo categoryID = new TypeInfo("Shield");
	
	TypeInfo IMPACT_SHIELD = new TypeInfo("IMPACT_SHIELD");
	TypeInfo EM_SHIELD = new TypeInfo("EM_SHIELD");
	TypeInfo THERMAL_SHIELD = new TypeInfo("THERMAL_SHIELD");
	TypeInfo RADIATION_SHIELD = new TypeInfo("RADIATION_SHIELD");
	
	/* Get the various resistances */
	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
	
	/* Set the shield power level */
	BusRequirement setShieldLevel(double powerLevel);
}
