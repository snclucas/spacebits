package org.spacebits.components.sensors;

import org.spacebits.components.TypeInfo;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;

public class FractalSensorArray extends BasicSensorArray {


	public FractalSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	

	@Override
	public String describe() {
		return "Fractal sensor array.";
	}
	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	
	
	
	
	public static TypeInfo type() {
		return new TypeInfo("FractalSensorArray");
	}
	
	@Override
	public final TypeInfo getType() {
		return type();
	}

}
