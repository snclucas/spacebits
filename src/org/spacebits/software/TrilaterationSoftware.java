package org.spacebits.software;

import java.util.*;
import java.util.stream.Collectors;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.Archive;
import org.spacebits.components.computers.DataRecord;
import org.spacebits.components.computers.DataStore;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Location;
import org.spacebits.universe.SimpleLocation;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.structures.SubspaceBeacon;
import org.spacebits.utils.math.DistanceSolver;

public class TrilaterationSoftware extends AbstractSoftware implements Software {

	public TrilaterationSoftware(String name) {
		super(name);
	}


	public static TypeInfo type() {
		return new TypeInfo("NavigationSoftware");
	}


	@Override
	public String describe() {
		return "Software to perform trilateration.";
	}


	@Override
	public String getDescription() {
		return "Trilateration ";
	}


	@Override
	public String toString() {
		return getDescription() + " software";
	}


	@Override
	public TypeInfo getType() {
		return type();
	}



	private Coordinates calculatePosition() {



		DataStore dataStore = getSystemComputer().getStorageDevice();

		// Look for subspace beacons in navigation archive
		Map<String,DataRecord> subspaceBeacons = dataStore.getData(CelestialObject.category(), SubspaceBeacon.type());

		//Convert to locations
		List<Location> subspaceBeaconsLocations = new ArrayList<>();
		for (Map.Entry<String, DataRecord> entry : map.entrySet()) {
			Location loc = new SimpleLocation(entry.getKey(), ((CelestialObject)entry.getValue()).getCoordinates()   );
			subspaceBeaconsLocations.add(loc);
		}
		
		if(subspaceBeacons.size() < 3) {
			SystemMessage message = new SystemMessage(null, this, "Not enough beacons to triangulate.", getSystemComputer().getUniversalTime());
			//getSystemComputer().addSystemMessage(message);
		}
		else {

			Collections.sort(subspaceBeaconsLocations, (Location loc1, Location loc2) -> p1.firstName.compareTo(p2.firstName));


			
		//	DistanceSolver.solve(precision, x1, y1, z1, d1, x2, y2, z2, d2, x3, y3, z3, d3);
		}

		return null;//TODO

	}





}
