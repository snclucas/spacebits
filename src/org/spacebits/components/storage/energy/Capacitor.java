package org.spacebits.components.storage.energy;

import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public class Capacitor extends AbstractEnergyStorageDevice {

	public Capacitor(String name,
			BusComponentSpecification busResourceSpecification,
			double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification, storageCapacity, chargeRate,
				dischargeRate);
	}

	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	

	@Override
	public double getOperatingPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOperatingCPUThroughput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemStatus online() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	

}
