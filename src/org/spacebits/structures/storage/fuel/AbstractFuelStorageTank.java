package org.spacebits.structures.storage.fuel;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.consumables.Fuel;
import org.spacebits.exceptions.NoFuelInTankException;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public abstract class AbstractFuelStorageTank extends AbstractBusComponent implements FuelStorageTank {

	protected double amountOfFuelInTank;
	protected double capacity;
	protected double fuelLevel;

	protected Fuel fuel;

	public AbstractFuelStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification);

		setCapacity(capacity);
		this.fuelLevel = 0.0;
	}


	@Override
	public TypeInfo getCategoryId() {
		return categoryID;
	}


	@Override
	public double getMass() {
		if(fuel != null)
			return super.getMass() + fuel.getDensity() * amountOfFuelInTank;
		else 
			return super.getMass();
	}


	public Fuel getFuel() {
		if(fuel != null)
			return fuel;
		else 
			throw new NoFuelInTankException("No fuel in tank [" + getName() + "]");
	}




	public void setFuel(Fuel fuel, double fuelVolume) {
		this.fuel = fuel;
		if(fuelVolume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank = fuelVolume;
	}



	@Override
	public void removeFuel(double fuelVolume) {
		if(fuelVolume < amountOfFuelInTank)
			amountOfFuelInTank -= fuelVolume;
		else 
			amountOfFuelInTank = 0.0;

	}




	public void fillFuel(double fuelVolume) {
		getFuel();
		if(fuelVolume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank += fuelVolume;
	}


	public double getAmountOfFuelInTank() {
		return this.amountOfFuelInTank;
	}




	@Override
	public double getFuelLevel() {
		return getAmountOfFuelInTank() / getCapacity();
	}






	public double getCapacity() {
		return capacity;
	}


	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}



	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}



	@Override
	public double getOperatingCPUThroughput() {
		return getNominalCPUThroughput();
	}




	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}



}
