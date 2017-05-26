package org.spacebits.data;

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SignalResponse;
import org.spacebits.physics.Unit;
import org.spacebits.universe.CelestialConstants;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.EnvironmentData;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.utils.Utils;

public class LocalEnvironmentDataProvider implements EnvironmentDataProvider {
	
	public LocalEnvironmentDataProvider () {
	}
	
	
	public EnvironmentData getEnvironmentData(Coordinates coordinates) {
		double subspaceNoise = getSubspaceNoise(coordinates);
		
		List<CelestialObject> nearByStars = 
				Universe.getInstance().getLocationsByTypeCloserThan(Star.type(), coordinates, new BigDecimal(Configuration.distanceForEnvironmentData));

		if(nearByStars.size() == 0)
			return new EnvironmentData(0.0, 0.0, subspaceNoise);

		double luminosity = 0.0;
		for(CelestialObject celestial : nearByStars) {
			if(celestial instanceof Star) {
				Star star = ((Star)celestial);
				BigDecimal d = Utils.distanceToLocation(coordinates, star.getCoordinates(), Unit.One);
				SignalResponse response = star.getSensorSignalResponse().getSignalResponse(Sensor.OPTICAL, BigDecimal.ZERO);
				d = d.max(new BigDecimal(CelestialConstants.G_STAR_RADIUS));
				luminosity += response.getSignalStrength() / (4*Math.PI* (d.pow(2)).doubleValue() );
				System.out.println(star.getName() + " " + response.getSignalStrength() / (4*Math.PI* (d.pow(2)).doubleValue() ));
			}
		} 
		return new EnvironmentData(luminosity, 0.0, subspaceNoise);
	}
	
	
	public double getSubspaceNoise(Coordinates coordinates) {
		return 1.0;
	}
	
}
