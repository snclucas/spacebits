package org.spacebits.data;

import org.spacebits.universe.Coordinates;
import org.spacebits.universe.EnvironmentData;

public interface EnvironmentDataProvider {
	EnvironmentData getEnvironmentData(String spacecraftIdent);
	EnvironmentData getEnvironmentData(Coordinates coordinates);
	double getSubspaceNoise(Coordinates coordinates);
}
