package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.ComponentVisitor;
import org.spacebits.components.Identifiable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.status.SystemStatusMessage;

public interface Bus extends BusCommunicator, ComponentVisitor, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Bus");
	
	String getName();
	void setName(String name);

	List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType);
	List<SpacecraftBusComponent> getComponents();
	void addComponent(SpacecraftBusComponent component);
	void addComponents(List<SpacecraftBusComponent> components);
	
	SystemStatusMessage registerSystemComputer(SystemComputer computer);
	
	List<Engine> getEngines();
	List<SystemComputer> getComputers();
	List<CommunicationComponent> getCommunicationDevices();

	SystemComputer getSystemComputer();
	void setSystemComputer(SystemComputer computer);
	
	double getTotalCPUThroughputAvailable();
	double getTotalPowerAvailable();
	double getCurrentPowerRequirement();
    double getCurrentCPUThroughputRequirement();
    
    Spacecraft getSpacecraft();
	void setSpacecraft(Spacecraft spacecraft);
}
