package org.spacebits.components.sensors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.physics.Unit;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;
import org.spacebits.universe.UniverseDataProvider;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.UnknownObject;
import org.spacebits.utils.Utils;

public class LocalSensorResponseMediator implements SensorResponseMediator {

	UniverseDataProvider universeDataProvider = Configuration.getUniverseDataProvider();

	@Override
	public List<SensorResult> activeScan(String spacecraftIdent, double duration, double signalPropagationSpeed,
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile) {

		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = Universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal((duration * signalPropagationSpeed) / 2.0); // There and back
		
		List<CelestialObject> objects = 
				universeDataProvider.getLocationsCloserThan(Universe.getSpacecraftLocation(spacecraftIdent), maximumDistanceScanned);


		return results; 
	}


	public List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile) {
		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = Universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal(1000000 * Unit.Ly); 
		
		List<CelestialObject> objectsWithin1000Ly = 
				universeDataProvider.getLocationsCloserThan(Universe.getSpacecraftLocation(spacecraftIdent), maximumDistanceScanned);

		for(CelestialObject object : objectsWithin1000Ly) {
			BigDecimal distance = Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation);
			
			SignalResponse returnedSignalResponse = object.getSignalResponse(sensorProfile.getSensorType(), distance);
			// TODO maybe set celestial object as UNKNOWN if under a certain threshold?

			System.out.println(object + " " + 
			returnedSignalResponse.getSignalStrength() + " " + 
					returnedSignalResponse.getSignalDispersion() + " " + 
			distance.doubleValue()/Unit.Ly);
			
			if(returnedSignalResponse.getSignalStrength() > 1.0)
				object = new UnknownObject("Unknown object", object.getCoordinates(), object.getSensorSignalResponse());

			SensorResult result = new SensorResult(object, Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation), 
					Utils.vectorToLocation(object.getCoordinates(), spacecraftLocation, false), returnedSignalResponse);
			results.add(result);
		}
		return results;
	}


}
