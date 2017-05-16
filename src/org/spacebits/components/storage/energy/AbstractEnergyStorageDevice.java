package org.spacebits.components.storage.energy;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

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
	public TypeInfo getCategory() {
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
