package org.spacebits.software;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.status.SystemStatus;

public class SystemMessageTest {


	@Test
	public void testEngineManagementSoftwareNoEngine() {
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type.toString());	
		
		//Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		SystemStatus status = systemComputer.online();
		
		
		if(status.isOK() == false) {
			System.out.println("There was a problem onlining your system computer");
			System.exit(-1);
		}
		
		
		Message message = new SystemMessage(null, null,"{cmd:callVector,params:[1,0,0]}", 34.3);
		Map<Integer, Message> messages = systemComputer.getMessagingSystem().broadcastMessage(message);
		
		for(Message msg : messages.values()) {
			System.out.println(msg.getMessage());
		}
		
		List<SpacecraftBusComponent> components = systemComputer.findBusComponent(Engine.categoryID);
		
		Message targetedMessage = new SystemMessage(null, null,"Test targeted message", 34.3);
		Message mess = systemComputer.getMessagingSystem().sendMessageTo(targetedMessage, components.get(0));
		
		System.out.println(mess.getMessage());

	}

}

	