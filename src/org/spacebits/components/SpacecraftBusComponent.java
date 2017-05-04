package org.spacebits.components;

import org.spacebits.Diagnosable;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.status.StatusProvider;
import org.spacebits.status.SystemStatus;

public interface SpacecraftBusComponent extends PhysicalComponent, Diagnosable, StatusProvider, Identifiable {
	
	boolean isOnline();
	
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
	
	SystemComputer getSystemComputer();

	String describe();

	void accept(ComponentVisitor componentVisitor);

	SystemStatus online();
	
	double getUniversalTime();
}
