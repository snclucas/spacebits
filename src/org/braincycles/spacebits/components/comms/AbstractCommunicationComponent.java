package org.braincycles.spacebits.components.comms;

import java.util.List;

import org.braincycles.spacebits.algorithm.Model;
import org.braincycles.spacebits.algorithm.ModelInputs;
import org.braincycles.spacebits.algorithm.ModelResult;
import org.braincycles.spacebits.algorithm.SimpleRadioFrequencyPropagationModel;
import org.braincycles.spacebits.components.AbstractBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.physics.Physics;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractCommunicationComponent extends AbstractBusComponent implements CommunicationComponent {
	
	public static TypeInfo categoryID = new TypeInfo("CommunicationDevice");
	
	private static double MAX_POWER_LEVEL = 100;
	
	protected double range;
	protected double powerLevel;
	protected double efficiency = 0.3;
	protected double deviceNoiseLevel = Physics.dBm2W(-80); // GJ/s or GW
	
	protected List<String> recievedMessages;
	protected List<String> broadcastMessages;
	
	protected int broadcastMessageQueueSize = 10;
	
	protected Model propagationModel;
	

	public AbstractCommunicationComponent(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification);
		this.propagationModel = propagationModel;
	}
	
	
	@Override
	public TypeInfo getCategoryId() {
		return categoryID;
	}


	@Override
	public void broadcast(String message, int recipient) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecievedMessages() {
		return recievedMessages.size() > 0;
	}

	@Override
	public List<String> getMessages() {
		return recievedMessages;
	}

	@Override
	public boolean addMessageToQueue(String message) {
		if(broadcastMessageQueueSize != broadcastMessages.size()) {
			broadcastMessages.add(message);
			return true;
		}
		else {
			return false;
		}
	}


	public double getPowerLevel() {
		return powerLevel;
	}


	public void setPowerLevel(double powerLevel) {
		this.powerLevel = powerLevel;
	}
	
	
	public double getRange() {
		return getRange(this.powerLevel);
	}
	
	
	public double getRange(double powerLevel) {
		ModelInputs inputs = new ModelInputs();
		inputs.addInput("CALC_TYPE", SimpleRadioFrequencyPropagationModel.RANGE_CALC);
		inputs.addInput("POWER", getMaximumOperationalPower() * (powerLevel/100.00));
		inputs.addInput("NOISE", 0);
		inputs.addInput("EFFICIENCY", this.efficiency);
		inputs.addInput("RECIEVE_THRESHOLD", this.deviceNoiseLevel); //GJ/s
		inputs.addInput("MAX_POWER", getMaximumOperationalPower());

		ModelResult result = propagationModel.getResult(inputs);
		return result.getResult("CALCULATED_RANGE");
	}
	


	@Override
	public boolean requestRange(double powerLevel) {
		//XXX FIX!
		double calcedRange = getRange(powerLevel);
		return calcedRange >= range;
	}


	@Override
	public double getMaximumRange() {
		return getRange(MAX_POWER_LEVEL);
	}


	public double getEfficiency() {
		return efficiency;
	}


	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}


	@Override
	public void setDeviceNoiseLevel(double recieveThreshold) {
		this.deviceNoiseLevel = recieveThreshold;
	}
	
	@Override
	public double getDeviceNoiseLevel() {
		return this.deviceNoiseLevel;
	}


	@Override
	public void recieveMessage(Message message) {
		System.out.println("Message recieved by comm device: " + getName() + "\n " + message.getMessage());
		
	}
	
	
	

}
