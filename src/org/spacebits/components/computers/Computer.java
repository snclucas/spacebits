package org.spacebits.components.computers;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.software.Software;
import org.spacebits.spacecraft.Bus;
import org.spacebits.status.SystemStatusMessage;

public interface Computer extends SpacecraftBusComponent, BusCommunicator  {
	
	static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	static TypeInfo type() {
		return category();
	}
	
	SystemComputer getSystemComputer();

	// Software handling
	Software getSoftware(TypeInfo softwareType);
	boolean hasSoftware(TypeInfo softwareType);
	SystemStatusMessage loadSoftware(Software software);
	
	Bus getSpacecraftBus();
	double getMaxCPUThroughput();
	
	
	
	void registerSpacecraftBus(Bus bus);
}
