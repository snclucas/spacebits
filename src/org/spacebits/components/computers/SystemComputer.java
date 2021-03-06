package org.spacebits.components.computers;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.exceptions.ComponentConfigurationException;
import org.spacebits.physics.Unit;
import org.spacebits.software.MessageMediator;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.status.SystemStatusMessage;

public interface SystemComputer extends Computer {
	
	static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	static TypeInfo type() {
		return new TypeInfo("SystemComputer");
	}
	
	double getUniversalTime();
	
	SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement);

	MessageMediator getMessagingSystem();
	void setMessagingSystem(MessageMediator messagingSystem);
	Object getSystemData(String id);
	DataStore getStorageDevice();
	SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status);

	List<SystemStatusMessage> getSystemMessages();

	List<SystemStatusMessage> checkSystems();

	List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType) throws ComponentConfigurationException;
	List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory) throws ComponentConfigurationException;
	
	double getTotalCPUThroughputAvailable();
	double getTotalPowerAvailable();
	double getTotalPowerAvailable(Unit unit);
	
	double getTotalCurrentPower();
	double getTotalCurrentPower(Unit unit);
    double getTotalCurrentCPUThroughput();

    List<Engine> getEngines();
	List<SystemComputer> getComputers();
	List<CommunicationComponent> getCommunicationDevices();
}
