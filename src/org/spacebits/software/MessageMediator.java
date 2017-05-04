package org.spacebits.software;

import java.util.Map;

import org.spacebits.components.SpacecraftBusComponent;

public interface MessageMediator extends Software {
	
	Message sendMessageTo(Message message, SpacecraftBusComponent component);
	
	Map<Integer, Message> broadcastMessage(Message message);
	 
    boolean addComponent(SpacecraftBusComponent component);

}
