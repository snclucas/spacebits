package org.braincycles.spacebits.universe;

import java.math.BigDecimal;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.computers.ArchivableData;

public interface Location extends ArchivableData {
	
	TypeInfo categoryID = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
}
