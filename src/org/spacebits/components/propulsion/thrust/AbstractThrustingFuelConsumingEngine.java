package org.spacebits.components.propulsion.thrust;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.profiles.FuelConsumptionProfile;
import org.spacebits.profiles.SimpleLinearFuelConsumptionProfile;
import org.spacebits.profiles.ThrustProfile;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public abstract class AbstractThrustingFuelConsumingEngine extends AbstractThrustingEngine implements FuelConsumingEngine {

	protected FuelConsumptionProfile fuelConsumptionProfile;
	protected FuelSubSystem fuelSubSystem;
	
	public AbstractThrustingFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustProfile thrustModel, FuelConsumptionProfile fuelConsumptionModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, 
				engineVector, vectored);	
		this.fuelConsumptionProfile = fuelConsumptionModel;
	}
	

	public AbstractThrustingFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust,
				engineVector, vectored);	
		this.fuelConsumptionProfile = new SimpleLinearFuelConsumptionProfile("Linear model");
	}
	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = super.online();
		
		List<SpacecraftBusComponent> busComponents = getSystemComputer().findBusComponent(FuelSubSystem.typeID);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {	
				if( ((FuelSubSystem)component).getFuelSubsystemType() == FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", getSystemComputer().getUniversalTime(), Status.OK);
					FuelSubSystem fuelSubSystem = (FuelSubSystem)busComponents.get(0);
					if(fuelSubSystem.hasFuelTanks() == false)
						systemStatus.addSystemMessage("No fuel storage tanks found", 
								getSystemComputer().getUniversalTime(), Status.WARNING);
					else
						systemStatus.addSystemMessage(fuelSubSystem.getFuelTanks().size() +  " fuel tank(s) found", getUniversalTime(), Status.OK);
				}
			}
		}
		else {
			systemStatus.addSystemMessage("No fuel subsystem found", 
					getSystemComputer().getUniversalTime(), Status.WARNING);
		}
		systemStatus.addSystemMessage(getName() + " online.", getSystemComputer().getUniversalTime(), Status.OK);
		return systemStatus;
	}


	@Override
	public double getFuelConsumptionRate() {
		return fuelConsumptionProfile.getNormalizedFuelConsumption(this.powerLevel);
	}
	
	
	@Override
	public double getFuelConsumptionRate(double powerLevel) {
		return fuelConsumptionProfile.getNormalizedFuelConsumption(powerLevel);
	}
	
	
	@Override
	public void setFuelSubSystem(FuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}
	
	
	@Override
	public FuelConsumptionProfile getFuelConsumptionProfile() {
		return this.fuelConsumptionProfile;
	}


	@Override
	public void setFuelConsumptionProfile(FuelConsumptionProfile fuelConsumptionProfile) {
		this.fuelConsumptionProfile = fuelConsumptionProfile;
	}

}
