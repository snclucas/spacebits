package org.braincycles.spacebits.software;

import org.braincycles.spacebits.components.Identifiable;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.computers.SystemComputer;
import org.braincycles.spacebits.status.StatusProvider;

public interface Software extends StatusProvider, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Software");
	
	void setComputer(SystemComputer computer);
	
	String getDescription();

}
