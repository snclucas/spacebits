package org.spacebits.software;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	protected SystemComputer computer;

	
	public AbstractSoftware(String name) {
		this.name = name;
	}

	
	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
	@Override
	public int getId() {
		return this.hashCode();
	}
	
	@Override
	public void setComputer(SystemComputer computer) {
		this.computer = computer;
	}


	@Override
	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	

}
