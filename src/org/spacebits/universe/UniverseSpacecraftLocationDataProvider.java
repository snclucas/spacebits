package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.Map;

import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;

public interface UniverseSpacecraftLocationDataProvider {
	void addSpacecraft(Spacecraft spacecraft);
	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);	
	void updateSpacecraftLocation(String spacecraftIdent, Location location);
	Coordinates getSpacecraftLocation(String spacecraftIdent);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
	Map<String,Coordinates> getSpacecraftWithinRangeOfLocation(Location location, BigDecimal range);
}
