package org.spacebits.software;

import java.util.Map;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.status.SystemStatusMessage;

public interface MessageMediator extends Software {
	
	Message sendMessageTo(Message message, SpacecraftBusComponent component);
	
	Map<String, Message> broadcastMessage(Message message);
	 
	SystemStatusMessage addComponent(SpacecraftBusComponent component);

}
