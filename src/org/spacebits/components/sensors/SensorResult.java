package org.spacebits.components.sensors;

import java.math.BigDecimal;

import org.spacebits.Configuration;
import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;
import org.spacebits.universe.NavigationVector;
import org.spacebits.universe.celestialobjects.CelestialObject;

public class SensorResult implements Identifiable {
	
	private CelestialObject celestialObject;
	private BigDecimal distance;
	private NavigationVector navigationVector;
	private SignalResponse signalResponse;
	private String ident;
	
	
	public SensorResult(CelestialObject celestialObject, BigDecimal distance,
			NavigationVector navigationVector, SignalResponse signalResponse) {
		super();
		this.celestialObject = celestialObject;
		this.distance = distance;
		this.navigationVector = navigationVector;
		this.signalResponse = signalResponse;
		this.ident = Configuration.getUUID();
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


	@Override
	public TypeInfo getType() {
		return new TypeInfo("SensorResult");
	}


	@Override
	public TypeInfo getCategory() {
		return new TypeInfo("SensorResult");
	}


	@Override
	public String getName() {
		return "SensorResult" + "-" + celestialObject.getName();
	}


	@Override
	public String getIdent() {
		return ident;
	}


	@Override
	public String describe() {
		return "Sensor result";
	}

	
	
}
