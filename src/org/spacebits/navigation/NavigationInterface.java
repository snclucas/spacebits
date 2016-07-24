package org.spacebits.navigation;

import org.spacebits.universe.Coordinates;

public interface NavigationInterface {
	
	void getVectorToCoordinates(Coordinates coordinates);
	
	Coordinates getSpacecraftLocation();

}
