package org.spacebits.spacecraft;

import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;


public abstract class AbstractSpacecraft extends AbstractBusComponent implements Spacecraft {
	
	private double currentSystemPowerRequirement;
	private double currentSystemCPUThroughputRequirement;
	
	private double spacecraftBusComponentsVolumeRequirement;
	private double spacecraftBusComponentsMass;
	
	protected boolean systemsOnline;
	protected Hull hull;

	protected List<SpacecraftBusComponent> components;
	
	protected Bus bus;
	
	
	public AbstractSpacecraft(String name, Hull hull) {
		super(name, new BusComponentSpecification());
		components = new ArrayList<SpacecraftBusComponent>();
		this.name = name;
		setHull(hull);
		systemsOnline = false;
		this.bus = new SpacecraftBus("Spacecraft bus"); ;
		this.bus.setSpacecraft(this);
	}

	@Override
	public TypeInfo getCategoryId() {
		return Spacecraft.categoryID;
	}
	
	
	

	public Bus getSpacecraftBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	

	@Override
	public SystemStatus online() {
		SystemStatus status = new SystemStatus(this);
		
		BasicSpacecraftFirmware.scanSpacecraftComponents(bus);
		
		if(BasicSpacecraftFirmware.bootstrapSystemComputer(bus) == false) {
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
			}
		}
		return status;
	}
	
	
//	@Override
//	public List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType) {
//		return bus.findBusComponent(this, componentType);
//		//return BasicSpacecraftFirmware.findBusComponent(this, componentType);
//	}
	
	protected boolean isSystemsOnline() {
		return systemsOnline;
	}
	

	public Hull getHull() {
		return hull;
	}


	public void setHull(Hull hull) {
		this.hull = hull;
		//addComponent(hull);
	}


	

	
	
	//@Override
	//public List<SystemComputer> getComputers() {
	//	return BasicSpacecraftFirmware.getComputers(this);
	//}
	
	
	@Override
	public void addComponent(SpacecraftBusComponent component) {
		components.add(component);
	}
	
	
	@Override
	public void addComponents(List<SpacecraftBusComponent> components) {
		components.addAll(components);
	}


	

	
	@Override
	public SystemComputer getSystemComputer() {
		return bus.getSystemComputer();
	}

	
	public void setSystemComputer(SystemComputer systemComputer) {
		bus.setSystemComputer(systemComputer);
	}
	
	
	public List<SpacecraftBusComponent> getComponents() {
		return components;
	}


	public void setComponents(List<SpacecraftBusComponent> components) {
		this.components = components;
	}


	//Hull delegate methods
	
	public double getLength() {
		return hull.getLength();
	}


	public double getWidth() {
		return hull.getWidth();
	}


	public double getVolume() {
		return hull.getVolume();
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


	
	public double getTotalMassOfSpacecraftBusComponents() {
		return spacecraftBusComponentsMass;
	}


	public double getTotalVolumeOfSpacecraftBusComponents() {
		//Adjust total volume calculation for the hull as the hull actually provides volume not uses it.
		spacecraftBusComponentsVolumeRequirement -= this.getHull().getVolume();
		return spacecraftBusComponentsVolumeRequirement;
	}


	public double getTotalPowerRequirementOfSpacecraftBusComponents() {
		return BasicSpacecraftFirmware.getTotalPowerAvailable(bus);
	}


	public double getTotalCPURequirementOfSpacecraftBusComponents() {
		return BasicSpacecraftFirmware.getTotalCPUThroughputAvailable(bus);
	}
	
	
	@Override
	public void visit(SpacecraftBusComponent component) {
		spacecraftBusComponentsMass += component.getMass();
		spacecraftBusComponentsVolumeRequirement += component.getVolume();
		currentSystemPowerRequirement += component.getNominalPower();
		currentSystemCPUThroughputRequirement += component.getNominalCPUThroughput();
		System.out.println(component.getName() + " " + component.getMass() + " kg " + 
				component.getVolume() + " m3 " + 
				component.getNominalPower() + " W " + component.getNominalCPUThroughput() + " kFLOPS ");
	}

	
	
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
