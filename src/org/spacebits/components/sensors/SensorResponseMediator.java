package org.spacebits.components.sensors;

import java.util.List;

public interface SensorResponseMediator {
	
	List<SensorResult> activeScan(int spacecraftIdent, double duration, double signalPropagationSpeed, 
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile);
	
	List<SensorResult> passiveScan(int spacecraftIdent, double duration, SensorProfile sensorProfile);

}
