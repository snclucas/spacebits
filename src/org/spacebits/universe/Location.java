package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public interface Location extends Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
}
