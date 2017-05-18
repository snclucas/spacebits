package org.spacebits.components;

import org.spacebits.components.computers.SystemComputer;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Bus;
import org.spacebits.status.StatusProvider;
import org.spacebits.status.SystemStatusMessage;

public interface SpacecraftBusComponent extends Component, Diagnosable, StatusProvider,  Onlineable, BusCommunicator   {
	
	void registerWithBus(Bus bus);
	
	double getNominalPower();
	double getNominalPower(Unit unit);
	
	void setNominalPower(double nominalPower);
	
	double getNominalCPUThroughput();
	void setNominalCPUThroughput(double nominalCPUThroughput);
	
	double getMaximumOperationalPower();
	
	double getMaximumOperationalCPUThroughput();
	double getMaximumOperationalCPUThroughput(Unit unit);
	
	double getCurrentPower();
	double getCurrentPower(Unit unit);
	double getCurrentCPUThroughput();
	double getCurrentCPUThroughput(Unit unit);
	
	SystemComputer getSystemComputer();
	
	SystemStatusMessage registerSystemComputer(SystemComputer systemComputer);

	boolean isOnSpacecraftBus();
	void accept(ComponentVisitor componentVisitor);
	
	double getUniversalTime();
}
