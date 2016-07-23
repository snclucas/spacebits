package org.braincycles.spacebits.data;

import org.braincycles.spacebits.universe.Coordinates;
import org.braincycles.spacebits.universe.EnvironmentData;

public interface EnvironmentDataProvider {
	
	EnvironmentData getEnvironmentData(Coordinates coordinates);

}
