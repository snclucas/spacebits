package org.braincycles.spacebits.components.propulsion;

import org.braincycles.spacebits.components.BusCommunicator;
import org.braincycles.spacebits.components.Executable;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;

public interface Engine extends SpacecraftBusComponent, Executable, BusCommunicator {
	
	TypeInfo categoryID = new TypeInfo("Engine");
		
	EngineVector getEngineVector();
	
	boolean isVectored();
	
	double getPowerLevel();
	
	double getRequiredPower(double powerLevel);
    double getRequiredCPUThroughput(double powerLevel);

}
