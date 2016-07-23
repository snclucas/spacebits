package org.braincycles.spacebits.components.propulsion;

import org.braincycles.spacebits.components.propulsion.thrust.FuelSubSystem;

public interface FuelConsumer {
	double getFuelConsumptionRate();
	void setFuelSubSystem(FuelSubSystem fuelSubSystem);
}
