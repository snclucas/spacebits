package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.spacebits.Configuration;
import org.spacebits.exceptions.SpacecraftNotFoundException;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.utils.Utils;


public class LocalUniverseSpacecraftLocationDataProvider implements UniverseSpacecraftLocationDataProvider {
	
	private final Map<String,Spacecraft> spacecraftInUniverse = new HashMap<String, Spacecraft>();

	private final Map<String,Coordinates> spacecraftLocationInUniverse = new HashMap<String, Coordinates>();
	
	UniverseLocationDataProvider universeLocationDataProvider = Configuration.getUniverseLocationDataProvider();
	

	public LocalUniverseSpacecraftLocationDataProvider() {
		super();
	}


	@Override
	public void addSpacecraft(Spacecraft spacecraft) {
		/* Place new spacecraft 1AU away from Sol */
		CelestialObject sol = universeLocationDataProvider.getLocationByName("Sol");
		spacecraftInUniverse.put(spacecraft.getIdent(), spacecraft);
		spacecraftLocationInUniverse.put(spacecraft.getIdent(), sol.getCoordinates()
				.add(new Coordinates(new BigDecimal(1.0 * Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO)));
	}

	
	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		if(spacecraftLocationInUniverse.put(spacecraftIdent, coordinates) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}
	
	
	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Location location) {
		updateSpacecraftLocation(spacecraftIdent, location.getCoordinates());
	}

	
	@Override
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		if(spacecraftLocationInUniverse.get(spacecraftIdent) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return spacecraftLocationInUniverse.get(spacecraftIdent);
	}
	
	
	@Override
	public BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit) {
		Coordinates coordsSpacecraft1 = getSpacecraftLocation(spacecraftIdent1);
		Coordinates coordsSpacecraft2 = getSpacecraftLocation(spacecraftIdent1);
		return Utils.distanceToLocation(coordsSpacecraft1, coordsSpacecraft2, unit);
	}

	
	public Map<String,Coordinates> getSpacecraftWithinRangeOfLocation(Location location, BigDecimal range) {
		return spacecraftLocationInUniverse.entrySet().stream()
				.filter(map -> map.getValue().getCoordinates().equals(location.getCoordinates()))
				.collect(Collectors.toMap(p -> p.getKey(),  p -> p.getValue()));
	}

}
