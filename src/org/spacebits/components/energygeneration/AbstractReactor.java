package org.spacebits.components.energygeneration;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.propulsion.FuelConsumer;
import org.spacebits.components.propulsion.thrust.FuelSubSystem;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;

public abstract class AbstractReactor extends AbstractPowerGenerator implements FuelConsumer  {
	
	protected FuelSubSystem fuelSubSystem;
	
	public AbstractReactor(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);

	}

	
	@Override
	public void setFuelSubSystem(FuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}

	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		
		List<SpacecraftBusComponent> busComponents = systemComputer.findBusComponent(FuelSubSystem.categoryID);

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
