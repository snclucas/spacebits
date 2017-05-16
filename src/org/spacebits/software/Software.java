package org.spacebits.software;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.Computer;
import org.spacebits.status.StatusProvider;

public interface Software extends StatusProvider, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Software");
	
	void setComputer(Computer computer);
	
	String getDescription();

}
