package org.braincycles.spacebits.structures.storage.fuel;

import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.consumables.Fuel;

public interface FuelStorageTank extends SpacecraftBusComponent {
	
	TypeInfo categoryID = new TypeInfo("FuelStorageTank");

	double getCapacity();

	double getFuelLevel();

	void setFuel(Fuel fuel, double fuelVolume);
	
	void fillFuel(double fuelVolume);
	
	void removeFuel(double fuelVolume);
	
	Fuel getFuel();
	
	double getAmountOfFuelInTank();

}
