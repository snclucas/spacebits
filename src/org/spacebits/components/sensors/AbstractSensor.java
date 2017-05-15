package org.spacebits.components.sensors;

import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractSensor extends AbstractBusComponent implements Sensor {
	
	protected SensorProfile sensorProfile;

	public AbstractSensor(String name, BusComponentSpecification busResourceSpecification, 
			SensorProfile sensorProfile) {
		super(name, busResourceSpecification);
		this.sensorProfile = sensorProfile;
	}
	
	
	@Override
	public final TypeInfo getCategoryId() {
		return category;
	}
	
	
	@Override
	public double getSensorGain() {
		return sensorProfile.getSignalGain();
	}




	@Override
	public double getSensorThreshold() {
		return sensorProfile.getSignalThreshold();
	}




	@Override
	public List<SensorResult> activeScan(double duration,
			double signalPropagationSpeed, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {
		
		String spacecraftIdent = (String)(this.getSystemComputer().getSystemData("spaceraft-ident"));
		return activeScan(spacecraftIdent, duration, signalPropagationSpeed, signalStrength, propagationModel, sensorType);
	}
	
	

	private List<SensorResult> activeScan(String spacecraftIdent, double duration,
			double signalPropagationSpeed, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {

		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.activeScan(spacecraftIdent, duration, signalPropagationSpeed, signalStrength, propagationModel, sensorProfile);
	}

	@Override
	public List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile) {
		String spacecraftIdent = (String)(this.getSystemComputer().getSystemData("spaceraft-ident"));
		return passiveScan(spacecraftIdent, duration, sensorProfile);
	}


	private List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile) {
		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.passiveScan(spacecraftIdent, duration, sensorProfile);
	}

	


	@Override
	public SensorProfile getSensorProfile() {
		return sensorProfile;
	}




	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	
	

}
