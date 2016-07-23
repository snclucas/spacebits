package org.braincycles.spacebits.spacecraft;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.software.Message;
import org.braincycles.spacebits.status.SystemStatus;
import org.braincycles.spacebits.structures.hulls.Hull;

public class SimpleSpacecraft extends AbstractSpacecraft {
	
	public static TypeInfo typeID = new TypeInfo("SimpleSpacecraft");

	public SimpleSpacecraft(String name, Hull hull) {
		super(name, hull);
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}
	
	
	@Override
	public double getOperatingCPUThroughput() {
		return getNominalCPUThroughput();
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		return systemStatus;
	}
	

	@Override
	public void recieveMessage(Message message) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public String describe() {
		return "Simple spacecraft";
	}

	



	

}
