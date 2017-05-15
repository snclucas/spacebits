package org.spacebits.spacecraft;

import java.util.List;
import java.util.UUID;

import org.spacebits.components.Component;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.DataRecord;
import org.spacebits.components.computers.SpacecraftData;
import org.spacebits.exceptions.ComponentConfigurationException;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;


public abstract class AbstractSpacecraft implements Spacecraft {
	
	private String name;
	private boolean online;
	
	private double spacecraftBusComponentsVolumeRequirement;
	private double spacecraftBusComponentsMass;
	
	protected boolean systemsOnline;
	protected Hull hull;
	
	protected Bus bus;
	
	private String ident = UUID.randomUUID().toString().replaceAll("-", "");
	
	
	
	public AbstractSpacecraft(String name, Hull hull) {
		this.name = name;
		setHull(hull);
		this.bus = new SpacecraftBus("Spacecraft bus", this);
		this.bus.setSpacecraft(this);
		systemsOnline = false;
	}

	@Override
	public String getName() {
		return this.name;
	}


	@Override
	public TypeInfo getCategoryId() {
		return Spacecraft.category;
	}
	
	@Override
	public Bus getSpacecraftBus() {
		return bus;
	}

	@Override
	public void setSpacecraftBus(Bus bus) {
		this.bus = bus;
	}
	
	@Override
	public boolean isOnline() {
		return this.online;
	}


	@Override
	public SystemStatus online() {
		SystemStatus status = new SystemStatus(this);
		
		SpacecraftFirmware.scanSpacecraftComponents(bus);
		
		if(SpacecraftFirmware.bootstrapSystemComputer(bus) == false) {
			status.addSystemMessage("No system computer found! Aborting spacecraft onlining.", 11, Status.CRITICAL);
			systemsOnline = false;
			online = false;
			return status;
		}
		else {
			//Online the system computer
			SystemStatus systemComputerStatus = bus.getSystemComputer().online();
			status.mergeSystemStatus(systemComputerStatus);
			if(status.isOK()) {
				systemsOnline = true;
				online = true;
				
				DataRecord data = new DataRecord("spaceraft-ident", 
						SpacecraftData.category, getSpacecraftBus().getSpacecraft().getIdent());
				
				bus.getSystemComputer().getStorageDevice().saveData(data);
				
			}
		}
		return status;
	}
	
	
	protected boolean isSystemsOnline() {
		return systemsOnline;
	}
	
	@Override
	public Hull getHull() {
		return hull;
	}


	private void setHull(Hull hull) {
		this.hull = hull;
	}


	@Override
	public void addComponent(Component component) {
		if(component instanceof SpacecraftBusComponent == false)
			throw new ComponentConfigurationException("Cano only add SpacecraftBusComponents");
		bus.addComponent((SpacecraftBusComponent)component);
		((SpacecraftBusComponent)component).registerWithBus(bus);
	}
	
	
	public List<SpacecraftBusComponent> getComponents() {
		return bus.getComponents();
	}


	//Hull delegate methods
	@Override
	public double getLength() {
		return hull.getLength();
	}

	@Override
	public double getWidth() {
		return hull.getWidth();
	}

	@Override
	public double getVolume() {
		return hull.getVolume();
	}
	
	@Override
	public double getMass() {
		return hull.getMass() + bus.getComponents().stream().mapToDouble(f-> f.getMass()).sum();
	}


	public double getImpactResistance() {
		return hull.getImpactResistance();
	}


	public double getEMResistance() {
		return hull.getEMResistance();
	}


	public double getRadiationResistance() {
		return hull.getRadiationResistance();
	}


	public double getThermalResistance() {
		return hull.getThermalResistance();
	}


	
	public double getTotalMassOfComponents() {
		return spacecraftBusComponentsMass;
	}


	public double getTotalVolumeOfComponents() {
		//Adjust total volume calculation for the hull as the hull actually provides volume not uses it.
		spacecraftBusComponentsVolumeRequirement -= this.getHull().getVolume();
		return spacecraftBusComponentsVolumeRequirement;
	}


	public double getTotalPowerRequirementOfSpacecraftBusComponents() {
		return SpacecraftFirmware.getTotalPowerAvailable(bus);
	}


	public double getTotalCPURequirementOfSpacecraftBusComponents() {
		return SpacecraftFirmware.getTotalCPUThroughputAvailable(bus);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((hull == null) ? 0 : hull.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSpacecraft other = (AbstractSpacecraft) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (hull == null) {
			if (other.hull != null)
				return false;
		} else if (!hull.equals(other.hull))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	@Override
	public String getIdent() {
		return ident;
	}

	
	
	
	

//	@Override
//	public void visit(SpacecraftBusComponent component) {
//		spacecraftBusComponentsMass += component.getMass();
//		spacecraftBusComponentsVolumeRequirement += component.getVolume();
//		currentSystemPowerRequirement += component.getNominalPower();
//		currentSystemCPUThroughputRequirement += component.getNominalCPUThroughput();
//		System.out.println(component.getName() + " " + component.getMass() + " kg " + 
//				component.getVolume() + " m3 " + 
//				component.getNominalPower() + " W " + component.getNominalCPUThroughput() + " kFLOPS ");
//	}

	
	
	
//	// -- Private methods -- //
//	
//	
//	@Override
//	public double getTotalPowerAvailable() {
//		return BasicSpacecraftFirmware.getTotalPowerAvailable(this);
//	}
//	
//	
//	@Override
//	public double getTotalCPUThroughputAvailable() {
//		return BasicSpacecraftFirmware.getTotalCPUThroughputAvailable(this);
//	}
//	
//	
//	@Override
//	public double getCurrentPowerRequirement() {
//		return BasicSpacecraftFirmware.getCurrentSpacecraftBusPowerRequirement(this);
//	}
//
//	
//	@Override
//	public double getCurrentCPUThroughputRequirement() {
//		return BasicSpacecraftFirmware.getCurrentSpacecraftBusCPUThroughputRequirement(this);
//	}
	
	
/*	@Override
	public double[] getThrust() {
		double[] combinedThrust = new double[3];
		for(Engine engine : BasicSpacecraftFirmware.getEngines(this)) {
			double[] thrust = engine.getThrust(new double[]{});
			Utils.mergeArray(combinedThrust, thrust);
		}
		return combinedThrust;
	}*/
	
	
	

}
