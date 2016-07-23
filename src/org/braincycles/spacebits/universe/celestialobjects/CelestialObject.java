package org.braincycles.spacebits.universe.celestialobjects;

import java.math.BigDecimal;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.computers.ArchivableData;
import org.braincycles.spacebits.components.sensors.SignalResponse;
import org.braincycles.spacebits.universe.Location;

public interface CelestialObject extends Location, ArchivableData {
	
	TypeInfo categoryID = new TypeInfo("CelestialObject");

	SensorSignalResponseProfile getSensorSignalResponse();
	SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance);
	Location getLocation();
}
