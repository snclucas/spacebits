package org.braincycles.spacebits.spacecraft;

import java.util.List;

import org.braincycles.spacebits.components.BusCommunicator;
import org.braincycles.spacebits.components.ComponentVisitor;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.comms.CommunicationComponent;
import org.braincycles.spacebits.components.computers.SystemComputer;
import org.braincycles.spacebits.components.propulsion.Engine;
import org.braincycles.spacebits.structures.hulls.Hull;

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
