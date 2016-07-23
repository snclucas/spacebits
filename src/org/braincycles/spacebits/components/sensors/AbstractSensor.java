package org.braincycles.spacebits.components.sensors;

import java.util.List;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.components.AbstractBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractSensor extends AbstractBusComponent implements Sensor {
	
	protected SensorProfile sensorProfile;

	public AbstractSensor(String name, BusComponentSpecification busResourceSpecification, 
			SensorProfile sensorProfile) {
		super(name, busResourceSpecification);
		this.sensorProfile = sensorProfile;
	}
	
	
	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
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
	public List<SensorResult> activeScan(int spacecraftIdent, double duration,
			double signalPropagationSpeed, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {

		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.activeScan(spacecraftIdent, duration, signalPropagationSpeed, signalStrength, propagationModel, sensorProfile);
	}
	

	@Override
	public List<SensorResult> passiveScan(int spacecraftIdent, double duration, SensorProfile sensorProfile) {
		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.passiveScan(spacecraftIdent, duration, sensorProfile);
	}



	


	@Override
	public SensorProfile getSensorProfile() {
		return sensorProfile;
	}


	@Override
	public void recieveMessage(Message message) {
		System.out.println("Message recieved by comm device: " + getName() + "\n " + message.getMessage());
		
	}



	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
