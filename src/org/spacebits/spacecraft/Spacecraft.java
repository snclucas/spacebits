package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.Component;
import org.spacebits.components.Diagnosable;
import org.spacebits.components.Onlineable;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.status.StatusProvider;
import org.spacebits.structures.hulls.Hull;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable {
	
	TypeInfo category = new TypeInfo("Spacecraft");
	
	String getName();
	String getIdent();
	
	double getVolume();
	double getMass();
	double getLength();
	double getWidth();
	
	double getTotalMassOfComponents();
	double getTotalVolumeOfComponents();
	
	void addComponent(Component component);
	List<SpacecraftBusComponent> getComponents();

	
	Hull getHull();
	
	Bus getSpacecraftBus();
	void setSpacecraftBus(Bus bus);
}
