package org.spacebits.components.propulsion;

import org.spacebits.components.propulsion.thrust.FuelSubSystem;

public interface FuelConsumer {
	double getFuelConsumptionRate();
	void setFuelSubSystem(FuelSubSystem fuelSubSystem);
}
