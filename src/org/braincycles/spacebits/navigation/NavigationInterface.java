package org.braincycles.spacebits.navigation;

import org.braincycles.spacebits.universe.Coordinates;

public interface NavigationInterface {
	
	void getVectorToCoordinates(Coordinates coordinates);
	
	Coordinates getSpacecraftLocation();

}
