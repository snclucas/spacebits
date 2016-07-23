package org.braincycles.spacebits.components.propulsion.thrust;

import java.util.List;

import org.braincycles.spacebits.algorithm.thrust.ThrustAlgorithm;
import org.braincycles.spacebits.components.SpacecraftBusComponent;
import org.braincycles.spacebits.components.comms.Status;
import org.braincycles.spacebits.components.propulsion.AbstractEngine;
import org.braincycles.spacebits.components.propulsion.EngineVector;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.status.SystemStatus;

public abstract class AbstractFuelConsumingEngine extends AbstractEngine implements FuelConsumingEngine {

	protected FuelSubSystem fuelSubSystem;
	
	public AbstractFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustAlgorithm thrustModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel,
				engineVector, vectored);	
	}
	

	public AbstractFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust,
				engineVector, vectored);	
	}
	
	
	@Override
	public double getFuelConsumptionRate() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setFuelSubSystem(FuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}

	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = super.online();
		
		List<SpacecraftBusComponent> busComponents = systemComputer.findBusComponent(FuelSubSystem.typeID);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {	
				if( ((FuelSubSystem)component).getFuelSubsystemType() == FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", systemComputer.getUniversalTime(), Status.OK);
					FuelSubSystem fuelSubSystem = (FuelSubSystem)busComponents.get(0);
					if(fuelSubSystem.hasFuelTanks() == false)
						systemStatus.addSystemMessage("No fuel storage tanks found", 
								systemComputer.getUniversalTime(), Status.WARNING);
					else
						systemStatus.addSystemMessage(fuelSubSystem.getFuelTanks().size() +  " fuel tank(s) found", systemComputer.getUniversalTime(), Status.OK);
				}
			}
		}
		else {
			systemStatus.addSystemMessage("No fuel subsystem found", 
					systemComputer.getUniversalTime(), Status.WARNING);
		}
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		return systemStatus;
	}





}
