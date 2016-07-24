package org.spacebits.components.sensors;

import java.math.BigDecimal;

import org.spacebits.universe.NavigationVector;
import org.spacebits.universe.celestialobjects.CelestialObject;

public class SensorResult {
	
	private CelestialObject celestialObject;
	private BigDecimal distance;
	private NavigationVector navigationVector;
	private SignalResponse signalResponse;
	
	
	public SensorResult(CelestialObject celestialObject, BigDecimal distance,
			NavigationVector navigationVector, SignalResponse signalResponse) {
		super();
		this.celestialObject = celestialObject;
		this.distance = distance;
		this.navigationVector = navigationVector;
		this.signalResponse = signalResponse;
	}


	public CelestialObject getCelestialObject() {
		return celestialObject;
	}


	public BigDecimal getDistance() {
		return distance;
	}


	public NavigationVector getNavigationVector() {
		return navigationVector;
	}


	public SignalResponse getSignalResponse() {
		return signalResponse;
	}

	
	
}
