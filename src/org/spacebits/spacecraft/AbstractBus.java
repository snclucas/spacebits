package org.spacebits.spacecraft;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.software.Message;
import org.spacebits.status.SystemStatusMessage;

public abstract class AbstractBus implements Bus {

	public static SpacecraftFirmware SpacecraftFirmware = new SpacecraftFirmware();
	protected Spacecraft spacecraft;
	
	protected SystemComputer systemComputer;

	private String name;

	public AbstractBus(String name, Spacecraft spacecraft) {
		this.name = name;
		this.spacecraft = spacecraft;
	}
	
	@Override
	public int getId() {
		return this.hashCode();
	}
	

	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(SpacecraftBusComponent component) {
		// TODO Auto-generated method stub

	}

	public Spacecraft getSpacecraft() {
		return spacecraft;
	}

	public void setSpacecraft(Spacecraft spacecraft) {
		this.spacecraft = spacecraft;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public List<SpacecraftBusComponent> findBusComponent(TypeInfo componentType) {
		return SpacecraftFirmware.findBusComponent(this, componentType);
	}

	
	@Override
	public List<SpacecraftBusComponent> getBusComponents() {
		return spacecraft.getComponents();
	}

	@Override
	public SystemStatusMessage registerSystemComputer(SystemComputer computer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBusComponent(SpacecraftBusComponent component) {
		spacecraft.addComponent(component);
	}


	@Override
	public void addBusComponents(List<SpacecraftBusComponent> components) {
		spacecraft.addComponents(components);
	}


	

	@Override
	public SystemComputer getSystemComputer() {
		return systemComputer;
	}

	@Override
	public void setSystemComputer(SystemComputer computer) {
		this.systemComputer = computer;
	}







	@Override
	public double getTotalPowerAvailable() {
		return SpacecraftFirmware.getTotalPowerAvailable(this);
	}


	@Override
	public double getTotalCPUThroughputAvailable() {
		return SpacecraftFirmware.getTotalCPUThroughputAvailable(this);
	}


	@Override
	public double getCurrentPowerRequirement() {
		return SpacecraftFirmware.getCurrentSpacecraftBusPowerRequirement(this);
	}


	@Override
	public double getCurrentCPUThroughputRequirement() {
		return SpacecraftFirmware.getCurrentSpacecraftBusCPUThroughputRequirement(this);
	}



}
