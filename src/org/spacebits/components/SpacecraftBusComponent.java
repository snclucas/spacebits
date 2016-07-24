package org.spacebits.components;

import org.spacebits.Diagnosable;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.status.StatusProvider;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;

public interface SpacecraftBusComponent extends Diagnosable, StatusProvider, Identifiable {
	
	boolean isOnline();

	void setName(String name);

	void setMass(double mass);

	void setVolume(double volume);

	double getMass();

	double getVolume();
	
	double getNominalPower();
	void setNominalPower(double nominalPower);
	
	double getNominalCPUThroughput();
	void setNominalCPUThroughput(double nominalCPUThroughput);
	
	double getMaximumOperationalPower();
	void setMaximumOperationalPower(double nominalPower);
	
	double getMaximumOperationalCPUThroughput();
	void setMaximumOperationalCPUThroughput(double maximumOperationalCPUThroughput);
	
	double getOperatingPower();
	double getOperatingCPUThroughput();
	
	SystemStatusMessage registerSystemComputer(SystemComputer computer);

	String describe();

	void accept(ComponentVisitor componentVisitor);

	SystemStatus online();
}
