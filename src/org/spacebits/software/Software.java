package org.spacebits.software;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.status.StatusProvider;

public interface Software extends StatusProvider, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Software");
	
	void setComputer(SystemComputer computer);
	
	String getDescription();

}
