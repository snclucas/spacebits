package org.spacebits.components.computers;

import java.util.Map;

import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.structures.SubspaceBeacon;

public abstract class AbstractNavigationComputer extends AbstractComputer implements NavigationComputer {

	Coordinates currentLocation;

	public AbstractNavigationComputer(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}




	@Override
	public SystemStatusMessage updateCurrentLocation() {
		//Refresh locations of objects using sensor sweeps
		
		DataStore dataStore = getSystemComputer().getStorageDevice();
		
		// Look for subspace beacons in navigation archive
		Map<String,DataRecord> subspaceBeacons = dataStore.getData(CelestialObject.category(), SubspaceBeacon.type());
		
		
		
		
		return null;
	}


	@Override
	public Coordinates getCurrentLocation() {






		return null;
	}


}
