package org.spacebits.structures.hulls;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.PhysicalComponent;
import org.spacebits.components.TypeInfo;

public interface Hull extends PhysicalComponent, BusCommunicator {
	
	TypeInfo category = new TypeInfo("Hull");
	
	TypeInfo RECTANGULAR = new TypeInfo("RECTANGULAR");
	TypeInfo SPHEROID = new TypeInfo("SPHEROID");
	
	//Hull mass fraction is the fraction of the hull thickness that is solid
	double HULL_VOLUME_FRACTION = 0.5;

	double getLength();
	double getWidth();
	double getThickness();

	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
	
	double getImpactResistanceModifier();
	double getEmResistanceModifier();
	double getThermalResistanceModifier();
	double getRadiationResistanceModifier();


}
