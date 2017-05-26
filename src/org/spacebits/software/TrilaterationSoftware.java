package org.spacebits.software;

import org.spacebits.components.TypeInfo;

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
	
	
	

}
