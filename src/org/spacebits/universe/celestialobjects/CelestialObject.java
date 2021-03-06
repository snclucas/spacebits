package org.spacebits.universe.celestialobjects;

import java.math.BigDecimal;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.sensors.SignalResponse;
import org.spacebits.universe.Location;

public interface CelestialObject extends Location{
	
	static TypeInfo category() {
		return new TypeInfo("CelestialObject");
	}

	SensorSignalResponseProfile getSensorSignalResponse();
	SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance);
	Location getLocation();
	
	
}
