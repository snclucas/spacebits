package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.ComponentVisitor;
import org.spacebits.components.Identifiable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.status.SystemStatusMessage;

public interface Bus extends BusCommunicator, ComponentVisitor, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Bus");
	static SpacecraftFirmware SpacecraftFirmware = new SpacecraftFirmware();
	
	String getName();
	void setName(String name);

	List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType);
	List<SpacecraftBusComponent> getBusComponents();
	
	void addBusComponent(SpacecraftBusComponent component);
	void addBusComponents(List<SpacecraftBusComponent> components);
	
	SystemStatusMessage registerSystemComputer(SystemComputer computer);
	
	SystemComputer getSystemComputer();
	void setSystemComputer(SystemComputer computer);
	
	double getTotalCPUThroughputAvailable();
	double getTotalPowerAvailable();
	double getCurrentPowerRequirement();
    double getCurrentCPUThroughputRequirement();
    
    Spacecraft getSpacecraft();
	void setSpacecraft(Spacecraft spacecraft);
	
}
