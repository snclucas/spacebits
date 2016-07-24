package org.spacebits.software;

import java.util.ArrayList;
import java.util.List;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;

public class SystemMessageService implements MessageMediator {

	
	private List<SpacecraftBusComponent> registeredComponents;
	
	public SystemMessageService() {
		registeredComponents = new ArrayList<SpacecraftBusComponent>();
	}
	
	@Override
	public boolean addComponent(SpacecraftBusComponent component) {
		return registeredComponents.add(component);	
	}
	

	@Override
	public boolean sendMessageTo(Message message, SpacecraftBusComponent component) {
		int componentIndex = registeredComponents.indexOf(component);
		boolean componentIsRegistered = componentIndex != -1;
		if(componentIsRegistered)
			((BusCommunicator)registeredComponents.get(componentIndex)).recieveMessage(message);
		return componentIsRegistered;
	}


	@Override
	public int broadcastMessage(Message message) {
		int counter = 0;
		for(SpacecraftBusComponent component : registeredComponents) {
			((BusCommunicator)component).recieveMessage(message);
			counter++;
		}
		return counter;
	}

	
	
	
}
