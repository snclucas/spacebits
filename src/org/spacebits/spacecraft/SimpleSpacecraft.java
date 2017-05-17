package org.spacebits.spacecraft;

import org.spacebits.components.TypeInfo;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;

public class SimpleSpacecraft extends AbstractSpacecraft {
	
	public static TypeInfo typeID = new TypeInfo("SimpleSpacecraft");

	public SimpleSpacecraft(String name, Hull hull) {
		super(name, hull);
	}
	
	
	@Override
	public TypeInfo getType() {
		return typeID;
	}
	

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		return systemStatus;
	}
	

	@Override
	public String describe() {
		return "Simple spacecraft";
	}



}
