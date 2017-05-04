package org.spacebits.components;

public interface PhysicalComponent {
	
	double getMass();

	double getVolume();
	
	void setName(String name);

	void setMass(double mass);

	void setVolume(double volume);

}
