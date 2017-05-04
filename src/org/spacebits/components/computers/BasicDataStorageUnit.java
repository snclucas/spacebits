package org.spacebits.components.computers;

import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.software.Message;
import org.spacebits.software.SystemMessage;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;


public class BasicDataStorageUnit extends AbstractDataStorageUnit  {
	
	public BasicDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	public static TypeInfo typeID = new TypeInfo("BasicDataStorageUnit");
	
	private final Map<String, ArchivableData> data = new HashMap<String, ArchivableData>();

	public TypeInfo getTypeId() {
		return typeID;
	}

	@Override
	public ArchivableData saveData(String id, ArchivableData dataRecord) {
		return data.put(id, dataRecord);
	}


	@Override
	public ArchivableData getData(String id) {
		return data.get(id);
	}
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	
	@Override
	public String describe() {
		return toString();
	}

	@Override
	public String toString() {
		return "BasicDataStorageUnit";
	}

}
