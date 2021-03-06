package org.spacebits.components.sensors;

import java.util.List;

import org.spacebits.components.Component;
import org.spacebits.components.Executable;
import org.spacebits.components.TypeInfo;

public interface Sensor extends Component, Executable {
	
	public static TypeInfo category() {
		return new TypeInfo("Sensor");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("Sensor");
	}
	
	SensorProfile getSensorProfile();
	
	final TypeInfo OPTICAL = new TypeInfo("Optical");
	final TypeInfo RADAR = new TypeInfo("Radar");
	final TypeInfo GRAVIMETRIC = new TypeInfo("Gravimetric");
	final TypeInfo MAGNETOMETRIC = new TypeInfo("Magnetometric");
	final TypeInfo SUBSPACE_RESONANCE = new TypeInfo("Subspace resonance");
	
	double getSensorGain();
	
	double getSensorThreshold();
	
	List<SensorResult> activeScan(double duration, double signalStrength, SignalPropagationModel propagationModel, int sensorType);
	
	List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile);
}
