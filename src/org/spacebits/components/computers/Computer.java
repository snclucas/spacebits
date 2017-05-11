package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;
import org.spacebits.software.Software;
import org.spacebits.spacecraft.Bus;
import org.spacebits.status.SystemStatusMessage;

public interface Computer {
	TypeInfo category = new TypeInfo("Computer");

	// Software handling
	Software getSoftware(TypeInfo softwareType);
	boolean hasSoftware(TypeInfo softwareType);
	SystemStatusMessage loadSoftware(Software software);
	
	Bus getSpacecraftBus();
	double getMaxCPUThroughput();
	
	
	
	void registerSpacecraftBus(Bus bus);
}
