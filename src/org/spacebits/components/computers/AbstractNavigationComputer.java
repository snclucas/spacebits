package org.spacebits.components.computers;

import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;

public abstract class AbstractNavigationComputer extends AbstractComputer implements NavigationComputer {

	Coordinates currentLocation;

	public AbstractNavigationComputer(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}




	@Override
	public SystemStatusMessage updateCurrentLocation() {
		//Refresh locations of objects using sensor sweeps
		Archive celestialLocations = getSystemComputer().getStorageDevice().getData(CelestialObject.category());
		
		
		
		
		return null;
	}


	@Override
	public Coordinates getCurrentLocation() {






		return null;
	}


}
