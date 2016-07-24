package org.spacebits.universe;

public class EnvironmentData {
	
	double solarFlux;
	double radiationFlux;
	
	public EnvironmentData(double solarFlux, double radiationFlux) {
		super();
		this.solarFlux = solarFlux;
		this.radiationFlux = radiationFlux;
	}

	public double getSolarFlux() {
		return solarFlux;
	}

	public void setSolarFlux(double solarFlux) {
		this.solarFlux = solarFlux;
	}

	public double getRadiationFlux() {
		return radiationFlux;
	}

	public void setRadiationFlux(double radiationFlux) {
		this.radiationFlux = radiationFlux;
	}
	
	
	
	

}
