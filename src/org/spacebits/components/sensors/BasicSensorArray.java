package org.spacebits.components.sensors;

import org.spacebits.components.comms.Status;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public abstract class BasicSensorArray extends AbstractSensor {
	
	
	
	protected int numberOfSensorElements;

	public BasicSensorArray(String name, BusComponentSpecification busResourceSpecification, 
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile);
		this.numberOfSensorElements = numberOfSensorElements;
	}
	
	
	
	

	@Override
	public double getSensorGain() {
		return numberOfSensorElements * sensorProfile.getSignalGain();
	}


	@Override
	public double getMass() {
		return super.getMass() * numberOfSensorElements;
	}



	@Override
	public double getVolume() {
		return super.getVolume() * numberOfSensorElements;
	}



	@Override
	public double getCurrentPower() {
		return busResourceSpecification.getNominalPower() * numberOfSensorElements;
	}

	@Override
	public double getCurrentCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout() * numberOfSensorElements;
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getSystemComputer().getUniversalTime(), Status.OK);
		return systemStatus; 
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		// TODO Change this
		systemStatus.addSystemMessage(
				"Level " + level + "diagnostics : All OK.", getSystemComputer().getUniversalTime(), Status.OK);

		return systemStatus;
	}





}
