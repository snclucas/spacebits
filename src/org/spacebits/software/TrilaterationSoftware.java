package org.spacebits.software;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.Archive;
import org.spacebits.components.computers.DataStore;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.structures.SubspaceBeacon;

public class TrilaterationSoftware extends AbstractSoftware implements Software {

	public TrilaterationSoftware(String name) {
		super(name);
	}
	

	public static TypeInfo type() {
		return new TypeInfo("NavigationSoftware");
	}
	

	@Override
	public String describe() {
		return "Software to perform trilateration.";
	}
	

	@Override
	public String getDescription() {
		return "Trilateration ";
	}

	
	@Override
	public String toString() {
		return getDescription() + " software";
	}


	@Override
	public TypeInfo getType() {
		return type();
	}
	
	
	
	private Coordinates calculatePosition() {
		DataStore dataStore = computer.getSystemComputer().getStorageDevice();
		
		Archive archive = dataStore.getData(CelestialObject.category());
		
		archive.filterByType(SubspaceBeacon.category());
		
		return null;//TODO
		
	}
	
	
	
	

}
