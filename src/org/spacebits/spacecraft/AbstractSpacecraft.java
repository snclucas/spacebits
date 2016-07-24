package org.spacebits.spacecraft;

import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.CommunicationComponent;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.utils.Utils;


public abstract class AbstractSpacecraft extends AbstractBusComponent implements Spacecraft {
	
	private double currentSystemPowerRequirement;
	private double currentSystemCPUThroughputRequirement;
	
	private double spacecraftBusComponentsVolumeRequirement;
	private double spacecraftBusComponentsMass;
	
	protected boolean systemsOnline;
	protected Hull hull;

	protected List<SpacecraftBusComponent> components;
	
	
	public AbstractSpacecraft(String name, Hull hull) {
		super(name, new BusComponentSpecification());
		components = new ArrayList<SpacecraftBusComponent>();
		this.name = name;
		setHull(hull);
		systemsOnline = false;
	}

	@Override
	public TypeInfo getCategoryId() {
		return Spacecraft.categoryID;
	}
	

	@Override
	public SystemStatus online() {
		SystemStatus status = new SystemStatus(this);
		
		BasicSpacecraftFirmware.scanSpacecraftComponents(this);
		
		if(BasicSpacecraftFirmware.bootstrapSystemComputer(this) == false) {
			status.addSystemMessage("No system computer found! Aborting spacecraft onlining.", 11, Status.CRITICAL);
			systemsOnline = false;
			online = false;
			return status;
		}
		else {
			//Online the system computer
			SystemStatus systemComputerStatus = systemComputer.online();
			status.mergeSystemStatus(systemComputerStatus);
			if(status.isOK()) {
				systemsOnline = true;
				online = true;
			}
		}
		return status;
	}
	
	
	@Override
	public List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType) {
		return BasicSpacecraftFirmware.findBusComponent(this, componentType);
	}
	
	protected boolean isSystemsOnline() {
		return systemsOnline;
	}
	

	public Hull getHull() {
		return hull;
	}


	public void setHull(Hull hull) {
		this.hull = hull;
		addComponent(hull);
	}


	

	
	
	@Override
	public List<SystemComputer> getComputers() {
		return BasicSpacecraftFirmware.getComputers(this);
	}
	
	
	@Override
	public void addComponent(SpacecraftBusComponent component) {
		components.add(component);
	}
	
	
	@Override
	public void addComponents(List<SpacecraftBusComponent> components) {
		components.addAll(components);
	}


	@Override
	public List<CommunicationComponent> getCommunicationDevices() {
		return BasicSpacecraftFirmware.getCommunicationDevices(this);
	}
	
	
	@Override
	public List<Engine> getEngines() {
		return BasicSpacecraftFirmware.getEngines(this);
	}

	
	@Override
	public SystemComputer getSystemComputer() {
		return this.systemComputer;
	}

	
	public void setSystemComputer(SystemComputer systemComputer) {
		this.systemComputer = systemComputer;
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
		return BasicSpacecraftFirmware.getTotalPowerAvailable(this);
	}


	public double getTotalCPURequirementOfSpacecraftBusComponents() {
		return BasicSpacecraftFirmware.getTotalCPUThroughputAvailable(this);
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

	
	
	// -- Private methods -- //
	
	
	@Override
	public double getTotalPowerAvailable() {
		return BasicSpacecraftFirmware.getTotalPowerAvailable(this);
	}
	
	
	@Override
	public double getTotalCPUThroughputAvailable() {
		return BasicSpacecraftFirmware.getTotalCPUThroughputAvailable(this);
	}
	
	
	@Override
	public double getCurrentPowerRequirement() {
		return BasicSpacecraftFirmware.getCurrentSpacecraftBusPowerRequirement(this);
	}

	
	@Override
	public double getCurrentCPUThroughputRequirement() {
		return BasicSpacecraftFirmware.getCurrentSpacecraftBusCPUThroughputRequirement(this);
	}
	
	
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
