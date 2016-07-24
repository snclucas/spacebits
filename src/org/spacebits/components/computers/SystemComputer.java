package org.spacebits.components.computers;

import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.MessageMediator;
import org.spacebits.software.Software;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.status.SystemStatusMessage;

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
