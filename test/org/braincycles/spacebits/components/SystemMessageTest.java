package org.braincycles.spacebits.components;

import org.braincycles.spacebits.components.comms.RadioCommunicator;
import org.braincycles.spacebits.components.propulsion.thrust.SimpleIonEngine;
import org.braincycles.spacebits.software.SystemMessage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SystemMessageTest {

	@Test
	public void testSystemMessage() {
		
		MockIdentifiableObject reciever = new MockIdentifiableObject(12, "Test reciever", RadioCommunicator.categoryID, RadioCommunicator.typeID);
		MockIdentifiableObject sender = new MockIdentifiableObject(123, "Test sender", SimpleIonEngine.categoryID, SimpleIonEngine.typeID);
	
		SystemMessage systemMessage = new SystemMessage(reciever, sender, "This is a test message", 1273);
		
		assertEquals("Reciever ID incorrect", reciever.getId(), systemMessage.getRecieverId());
		assertEquals("Sender ID incorrect", sender.getId(), systemMessage.getSenderId());
		assertEquals("Message incorrect", "This is a test message", systemMessage.getMessage());
		assertEquals("Universal date incorrect", 1273, systemMessage.getUniversalDate(), 0.001);
	}

}

class MockIdentifiableObject implements Identifiable {
	
	int id;
	String name;
	TypeInfo category;
	TypeInfo type;
	
	
	public MockIdentifiableObject(int id, String name, TypeInfo category, TypeInfo type) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.type = type;
	}
	

	@Override
	public TypeInfo getTypeId() {
		return type;
	}
	

	@Override
	public TypeInfo getCategoryId() {
		return category;
	}
	

	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public int getId() {
		return id;
	}
	
}
