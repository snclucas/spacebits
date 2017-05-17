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
	
	TypeInfo category = new TypeInfo("Bus");
	
	String getName();

	List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType);
	List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory);
	List<SpacecraftBusComponent> getComponents();
	
	void addComponent(SpacecraftBusComponent component);
	
	SystemStatusMessage registerSystemComputer(SystemComputer computer);
	
	SystemComputer getSystemComputer();
    
    Spacecraft getSpacecraft();
	void setSpacecraft(Spacecraft spacecraft);
}
