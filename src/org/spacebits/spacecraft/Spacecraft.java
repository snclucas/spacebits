package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.ComponentVisitor;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.structures.hulls.Hull;

public interface Spacecraft extends SpacecraftBusComponent, BusCommunicator, ComponentVisitor {
	
	TypeInfo categoryID = new TypeInfo("Spacecraft");
	
	String getName();
	void setName(String name);
	
	double getVolume();
	
	double getTotalMassOfSpacecraftBusComponents();
	double getTotalVolumeOfSpacecraftBusComponents();
	
	void addComponent(SpacecraftBusComponent component);
	void addComponents(List<SpacecraftBusComponent> components);
	List<SpacecraftBusComponent> getComponents();

	
	Hull getHull();
	
	Bus getSpacecraftBus();
}
