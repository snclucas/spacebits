package org.braincycles.spacebits.software;

import org.braincycles.spacebits.components.SpacecraftBusComponent;

public interface MessageMediator {
	
	boolean sendMessageTo(Message message, SpacecraftBusComponent component);
	
	int broadcastMessage(Message message);
	 
    boolean addComponent(SpacecraftBusComponent component);

}
