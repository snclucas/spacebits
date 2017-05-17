package org.spacebits.components.propulsion;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.Executable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;

public interface Engine extends SpacecraftBusComponent, Executable, BusCommunicator {
	
	public static TypeInfo category() {
		return new TypeInfo("Engine");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("Engine");
	}
		
	EngineVector getEngineVector();
	
	void setPowerLevel(double powerLevel);
	
	boolean isVectored();
	double getPowerLevel();
	double getRequiredPower(double powerLevel);
    double getRequiredCPUThroughput(double powerLevel);
}
