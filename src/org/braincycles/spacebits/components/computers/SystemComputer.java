package org.braincycles.spacebits.components.computers;

import java.util.List;

import org.braincycles.spacebits.components.BusCommunicator;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.software.MessageMediator;
import org.braincycles.spacebits.software.Software;
import org.braincycles.spacebits.spacecraft.BusRequirement;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.status.SystemStatusMessage;

public interface SystemComputer extends SpacecraftBusComponent, BusCommunicator {
	
	TypeInfo categoryID = new TypeInfo("Computer");
	
	// Software handling
	Software getSoftware(TypeInfo softwareType);
	boolean hasSoftware(TypeInfo softwareType);
	SystemStatusMessage loadSoftware(Software software);
	Spacecraft getSpacecraftBus();
	double getMaxCPUThroughput();
	
	void registerSpacecraftBus(Spacecraft spacecraft);
	
	double getUniversalTime();
	
	SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement);

	MessageMediator getMessagingSystem();
	void setMessagingSystem(MessageMediator messagingSystem);

	List<SystemStatusMessage> registerSpacecraftComponents();

	SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status);

	List<SystemStatusMessage> getSystemMessages();

	List<SystemStatusMessage> checkSystems();

	List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType);
	
	double getTotalCPUThroughputAvailable();
	double getTotalPowerAvailable();
	double getCurrentPowerRequirement();
    double getCurrentCPUThroughputRequirement();

}
