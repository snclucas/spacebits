package org.spacebits.components;

import org.spacebits.Diagnosable;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.spacecraft.Bus;
import org.spacebits.status.StatusProvider;

public interface SpacecraftBusComponent extends PhysicalComponent, Diagnosable, StatusProvider, Identifiable, Onlineable {
	
	void registerWithBus(Bus bus);
	
	double getNominalPower();
	double getNominalPower(double unit);
	
	void setNominalPower(double nominalPower);
	
	double getNominalCPUThroughput();
	void setNominalCPUThroughput(double nominalCPUThroughput);
	
	double getMaximumOperationalPower();
	
	double getMaximumOperationalCPUThroughput();
	
	double getCurrentPower();
	double getCurrentPower(double unit);
	double getCurrentCPUThroughput();
	
	SystemComputer getSystemComputer();

	boolean isOnSpacecraftBus();
	void accept(ComponentVisitor componentVisitor);
	
	double getUniversalTime();
}
