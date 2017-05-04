package org.spacebits.software;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;

public class SystemMessageService extends AbstractSoftware implements MessageMediator {

	public static TypeInfo typeID = new TypeInfo("MessageMediatorSoftware");
	
	private List<SpacecraftBusComponent> registeredComponents;
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	@Override
	public String getDescription() {
		return "System message mediator";
	}
	
	public SystemMessageService(String name, SystemComputer computer) {
		super(name, computer);
		registeredComponents = new ArrayList<SpacecraftBusComponent>();
	}
	
	@Override
	public boolean addComponent(SpacecraftBusComponent component) {
		return registeredComponents.add(component);	
	}
	

	@Override
	public Message sendMessageTo(Message message, SpacecraftBusComponent component) {
		int componentIndex = registeredComponents.indexOf(component);
		boolean componentIsRegistered = componentIndex != -1;
		if(componentIsRegistered)
			return ((BusCommunicator)registeredComponents.get(componentIndex)).recieveBusMessage(message);
		else
			return new SystemMessage(null, null, "Component not registered", computer.getUniversalTime());
	}


	@Override
	public Map<Integer, Message> broadcastMessage(Message message) {
		Map<Integer, Message> replies = new HashMap<Integer, Message>();
		for(SpacecraftBusComponent component : registeredComponents) {
			replies.put(component.getId(), ((BusCommunicator)component).recieveBusMessage(message));
		}
		return replies;
	}


}
