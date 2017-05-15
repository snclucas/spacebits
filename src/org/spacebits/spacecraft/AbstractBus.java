package org.spacebits.spacecraft;

import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.software.Message;
import org.spacebits.status.SystemStatusMessage;

public abstract class AbstractBus implements Bus {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		AbstractBus other = (AbstractBus) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (spacecraft == null) {
			if (other.spacecraft != null)
				return false;
		} else if (!spacecraft.equals(other.spacecraft))
			return false;
		return true;
	}




	protected Spacecraft spacecraft;
	
	protected final List<SpacecraftBusComponent> components = new ArrayList<SpacecraftBusComponent>();

	private final String name;

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
		if(component.getCategoryId() == SystemComputer.category)
			findComponent(SystemComputer.category).clear();
		this.components.add(component);
		component.registerWithBus(this);
	}


	

	@Override
	public SystemComputer getSystemComputer() {
		List<SpacecraftBusComponent> components = findComponent(SystemComputer.category);
		if(components.size() == 0)
			return null;
		return (SystemComputer)components.get(0);
	}



}
