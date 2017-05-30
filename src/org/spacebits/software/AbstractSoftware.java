package org.spacebits.software;

import org.spacebits.Configuration;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.Computer;
import org.spacebits.components.computers.SystemComputer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	private Computer computer;
	protected final String ident;
	
	public AbstractSoftware(String name) {
		this.name = name;
		this.ident = Configuration.getUUID();
	}

	
	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}
	
	@Override
	public String getIdent() {
		return ident;
	}
	
	@Override
	public void setComputer(Computer computer) {
		this.computer = computer;
	}


	@Override
	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public SystemComputer getSystemComputer() {
		return computer.getSystemComputer();
	}
	
	

}
