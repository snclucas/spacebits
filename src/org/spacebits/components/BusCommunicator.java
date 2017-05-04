package org.spacebits.components;

import org.spacebits.software.Message;

public interface BusCommunicator {
	
	Message recieveBusMessage(Message message);

}
