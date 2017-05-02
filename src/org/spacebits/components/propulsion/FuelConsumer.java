package org.spacebits.components.propulsion;

import org.spacebits.components.propulsion.thrust.FuelSubSystem;
import org.spacebits.profiles.FuelConsumptionProfile;

public interface FuelConsumer {
	double getFuelConsumptionRate();
	double getFuelConsumptionRate(double powerLevel);
	void setFuelSubSystem(FuelSubSystem fuelSubSystem);
	
	FuelConsumptionProfile getFuelConsumptionProfile();
	void setFuelConsumptionProfile(FuelConsumptionProfile fuelConsumptionProfile);
}
