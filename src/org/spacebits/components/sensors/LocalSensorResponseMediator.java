package org.spacebits.components.sensors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.data.EnvironmentDataProvider;
import org.spacebits.physics.Unit;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;
import org.spacebits.universe.UniverseLocationDataProvider;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.UnknownObject;
import org.spacebits.utils.Utils;

public class LocalSensorResponseMediator implements SensorResponseMediator {
	Universe universe = Universe.getInstance();
	UniverseLocationDataProvider universeDataProvider = Configuration.getUniverseLocationDataProvider();
	EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();

	@Override
	public List<SensorResult> activeScan(String spacecraftIdent, double duration,
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile) {
		
		double signalPropagationSpeed = universeDataProvider.getSignalPropagationSpeed(sensorProfile);

		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal((duration * signalPropagationSpeed) / 2.0); // There and back
		
		List<CelestialObject> objects = 
				universeDataProvider.getLocationsCloserThan(spacecraftLocation, maximumDistanceScanned);


		return results; 
	}


	public List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile) {
		List<SensorResult> results = new ArrayList<SensorResult>();
		Coordinates spacecraftLocation = universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal(1000000 * Unit.Ly.value()); 
		
		List<CelestialObject> objectsWithin1000Ly = 
				universeDataProvider.getLocationsCloserThan(spacecraftLocation, maximumDistanceScanned);

		for(CelestialObject object : objectsWithin1000Ly) {
			BigDecimal distance = Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation, Unit.One);
			
			SignalResponse returnedSignalResponse = object.getSignalResponse(sensorProfile.getSensorType(), distance);
			// TODO maybe set celestial object as UNKNOWN if under a certain threshold?

			System.out.println(object + " " + 
			returnedSignalResponse.getSignalStrength() + " " + 
					returnedSignalResponse.getSignalDispersion() + " " + 
			distance.doubleValue()/Unit.Ly.value());
			
			if(returnedSignalResponse.getSignalStrength() > 1.0)
				object = new UnknownObject("Unknown object", object.getCoordinates(), object.getSensorSignalResponse());

			SensorResult result = new SensorResult(object, Utils.distanceToLocation(object.getCoordinates(), spacecraftLocation, Unit.One), 
					Utils.vectorToLocation(object.getCoordinates(), spacecraftLocation, false), returnedSignalResponse);
			results.add(result);
		}
		return results;
	}


}
