package org.spacebits.components.sensors;

import org.spacebits.components.TypeInfo;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
	
	public static TypeInfo typeID = new TypeInfo("LinearSensorArray");

	public LinearSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	@Override
	public String describe() {
		return "Linear sensor array.";
	}
	
	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + ":\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}

}
