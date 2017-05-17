package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;
import org.spacebits.physics.Unit;

public interface Location extends Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
	NavigationVector vectorToLocation(Location location, boolean normalized);
	BigDecimal distanceToLocation(Location location, Unit unit);
}
