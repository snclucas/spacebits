package org.braincycles.spacebits.components.sensors;

import java.util.List;

import org.braincycles.spacebits.components.BusCommunicator;
import org.braincycles.spacebits.components.Executable;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;

public interface Sensor extends SpacecraftBusComponent, Executable, BusCommunicator  {
	
	TypeInfo categoryID = new TypeInfo("Sensor");
	
	SensorProfile getSensorProfile();
	
	final TypeInfo OPTICAL = new TypeInfo("Optical");
	final TypeInfo RADAR = new TypeInfo("Radar");
	final TypeInfo GRAVIMETRIC = new TypeInfo("Gravimetric");
	final TypeInfo MAGNETOMETRIC = new TypeInfo("Magnetometric");
	final TypeInfo SUBSPACE_RESONANCE = new TypeInfo("Subspace resonance");
	
	double getSensorGain();
	
	double getSensorThreshold();
	
	List<SensorResult> activeScan(int spacecraftIdent, double duration, double signalPropagationSpeed, 
			double signalStrength, SignalPropagationModel propagationModel, int sensorType);
	
	List<SensorResult> passiveScan(int spacecraftIdent, double duration, SensorProfile sensorProfile);
}
