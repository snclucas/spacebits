package org.spacebits.data;

import org.spacebits.universe.Coordinates;
import org.spacebits.universe.EnvironmentData;

public interface EnvironmentDataProvider {
	
	EnvironmentData getEnvironmentData(Coordinates coordinates);

}
