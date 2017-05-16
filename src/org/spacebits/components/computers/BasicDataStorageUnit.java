package org.spacebits.components.computers;

import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;


public class BasicDataStorageUnit extends AbstractDataStorageUnit  {

	public static TypeInfo type() {
		return new TypeInfo("BasicDataStorageUnit");
	}
	
	public BasicDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	private final Map<TypeInfo, Archive> dataArchives = new HashMap<TypeInfo, Archive>();

	
	public TypeInfo getType() {
		return type();
	}

	@Override
	public void saveData(DataRecord data) {
		if(dataArchives.containsKey(data.getRecordType())) {
			Archive archive = dataArchives.get(data.getRecordType());
			archive.put(data.getRecordID(), data);
		}
		else {
			Archive archive = new Archive();
			archive.put(data.getRecordID(), data);
			dataArchives.put(data.getRecordType(), archive);
		}
	}


	@Override
	public DataRecord getData(String id, TypeInfo typeInfo) {
		if(dataArchives.containsKey(typeInfo)) {
			Archive archive = dataArchives.get(typeInfo);
			return archive.get(id);
		}
		else {
			return null;
		}
	}
	
	

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
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

class Archive extends HashMap<String, DataRecord> {
	private static final long serialVersionUID = 925935940538264787L;
}
