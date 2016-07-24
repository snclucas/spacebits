package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.ComponentVisitor;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.structures.hulls.Hull;

public interface Spacecraft extends SpacecraftBusComponent, BusCommunicator, ComponentVisitor {
	
	TypeInfo categoryID = new TypeInfo("Spacecraft");
	
	String getName();
	void setName(String name);
	
	double getVolume();
	
	double getTotalMassOfSpacecraftBusComponents();
	double getTotalVolumeOfSpacecraftBusComponents();

	List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType);
	List<SpacecraftBusComponent> getComponents();
	void addComponent(SpacecraftBusComponent component);
	void addComponents(List<SpacecraftBusComponent> components);
	
	List<Engine> getEngines();
	List<SystemComputer> getComputers();
	List<CommunicationComponent> getCommunicationDevices();

	SystemComputer getSystemComputer();
	void setSystemComputer(SystemComputer computer);
	
	Hull getHull();
	
	double getTotalCPUThroughputAvailable();
	double getTotalPowerAvailable();
	double getCurrentPowerRequirement();
    double getCurrentCPUThroughputRequirement();
}
