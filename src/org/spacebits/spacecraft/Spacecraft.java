package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.Diagnosable;
import org.spacebits.components.Onlineable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;
import org.spacebits.structures.hulls.Hull;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable {
	
	TypeInfo categoryID = new TypeInfo("Spacecraft");
	
	String getName();
	void setName(String name);
	
	double getVolume();
	double getMass();
	double getLength();
	double getWidth();
	
	double getTotalMassOfSpacecraftBusComponents();
	double getTotalVolumeOfSpacecraftBusComponents();
	
	void addComponent(SpacecraftBusComponent component);
	void addComponents(List<SpacecraftBusComponent> components);
	List<SpacecraftBusComponent> getComponents();

	
	Hull getHull();
	
	Bus getSpacecraftBus();
	void setSpacecraftBus(Bus bus);
}
