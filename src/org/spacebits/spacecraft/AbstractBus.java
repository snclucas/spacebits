package org.spacebits.spacecraft;

import java.util.ArrayList;
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
	
	protected List<SpacecraftBusComponent> components = new ArrayList<SpacecraftBusComponent>();

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
		return category;
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
	public List<SpacecraftBusComponent> findComponent(TypeInfo componentType) {
		return SpacecraftFirmware.findBusComponent(this, componentType);
	}

	
	@Override
	public List<SpacecraftBusComponent> getComponents() {
		return this.components;
	}

	@Override
	public SystemStatusMessage registerSystemComputer(SystemComputer computer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComponent(SpacecraftBusComponent component) {
		this.components.add(component);
		component.registerWithBus(this);
		if(component.getCategoryId() == SystemComputer.category)
			setSystemComputer((SystemComputer)component);
	}


	@Override
	public void addComponents(List<SpacecraftBusComponent> components) {
		this.components.addAll(components);
	}


	

	@Override
	public SystemComputer getSystemComputer() {
		return systemComputer;
	}

	@Override
	public void setSystemComputer(SystemComputer computer) {
		this.systemComputer = computer;
	}


}
