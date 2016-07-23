package org.braincycles.spacebits.components.sensors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.universe.Coordinates;
import org.braincycles.spacebits.universe.Location;
import org.braincycles.spacebits.universe.Universe;
import org.braincycles.spacebits.universe.UniverseDataProvider;
import org.braincycles.spacebits.universe.celestialobjects.CelestialObject;
import org.braincycles.spacebits.universe.celestialobjects.UnknownObject;
import org.braincycles.spacebits.utils.Utils;

public class LocalSensorResponseMediator implements SensorResponseMediator {

	UniverseDataProvider universeDataProvider = Configuration.getUniverseDataProvider();

	@Override
	public List<SensorResult> activeScan(int spacecraftIdent, double duration, double signalPropagationSpeed,
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile) {

		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = Universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal((duration * signalPropagationSpeed) / 2.0); // There and back
		
		List<CelestialObject> objects = 
				universeDataProvider.getLocationsCloserThan(Universe.getSpacecraftLocation(spacecraftIdent), maximumDistanceScanned);


		return results; 
	}


	public List<SensorResult> passiveScan(int spacecraftIdent, double duration, SensorProfile sensorProfile) {
		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = Universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal(1000000 * Unit.Ly); 
		
		List<CelestialObject> objectsWithin1000Ly = 
				universeDataProvider.getLocationsCloserThan(Universe.getSpacecraftLocation(spacecraftIdent), maximumDistanceScanned);

		for(CelestialObject object : objectsWithin1000Ly) {
			BigDecimal distance = Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation);
			
			SignalResponse returnedSignalResponse = object.getSignalResponse(sensorProfile.getSensorType(), distance);
			// TODO maybe set celestial object as UNKNOWN if under a certain threshold?

			System.out.println(object + " " + returnedSignalResponse.getSignalStrength() + " " + returnedSignalResponse.getSignalDispersion() + " " + distance.doubleValue()/Unit.Ly);
			
			if(returnedSignalResponse.getSignalStrength() > 1.0)
				object = new UnknownObject(-122, "Unknown object", object.getCoordinates(), object.getSensorSignalResponse());

			SensorResult result = new SensorResult(object, Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation), 
					Utils.vectorToLocation(object.getCoordinates(), spacecraftLocation, false), returnedSignalResponse);
			results.add(result);
		}
		return results;
	}


}
