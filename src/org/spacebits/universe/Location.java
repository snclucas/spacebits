package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.ArchivableData;

public interface Location extends ArchivableData {
	
	TypeInfo categoryID = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
}
