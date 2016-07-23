package org.braincycles.spacebits.components.sensors;

import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.status.SystemStatus;

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
	public double getOperatingPower() {
		return busResourceSpecification.getNominalPower() * numberOfSensorElements;
	}

	@Override
	public double getOperatingCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout() * numberOfSensorElements;
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus; 
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		// TODO Change this
		systemStatus.addSystemMessage(
				"Level " + level + "diagnostics : All OK.", systemComputer.getUniversalTime(), Status.OK);

		return systemStatus;
	}





}
