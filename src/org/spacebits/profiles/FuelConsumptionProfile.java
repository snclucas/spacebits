package org.spacebits.profiles;

import org.spacebits.Diagnosable;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;

public interface FuelConsumptionProfile extends Diagnosable, StatusProvider {
	
	TypeInfo categoryID = new TypeInfo("FuelConsumptionProfile");

	double getNormalizedFuelConsumption(double powerLevel);
	double[][] getNormalizedFuelConsumptionProfile();
}
