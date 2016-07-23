package org.braincycles.spacebits.components.storage.energy;

import org.braincycles.spacebits.components.AbstractBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractEnergyStorageDevice extends AbstractBusComponent implements EnergyStorageDevice {
	
	protected double storageCapacity;
	protected double chargeRate;
	protected double dischargeRate;
	

	public AbstractEnergyStorageDevice(String name,
			BusComponentSpecification busResourceSpecification, double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification);
		this.storageCapacity = storageCapacity;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
	}
	
	
	@Override
	public TypeInfo getCategoryId() {
		return categoryID;
	}


	public double getStorageCapacity() {
		return storageCapacity;
	}


	public void setStorageCapacity(double storageCapacity) {
		this.storageCapacity = storageCapacity;
	}


	public double getChargeRate() {
		return chargeRate;
	}


	public void setChargeRate(double chargeRate) {
		this.chargeRate = chargeRate;
	}


	public double getDischargeRate() {
		return dischargeRate;
	}


	public void setDischargeRate(double dischargeRate) {
		this.dischargeRate = dischargeRate;
	}
	
}
