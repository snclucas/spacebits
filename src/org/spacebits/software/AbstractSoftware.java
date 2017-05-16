package org.spacebits.software;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.Computer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	protected Computer computer;

	
	public AbstractSoftware(String name) {
		this.name = name;
	}

	
	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}
	
	@Override
	public int getId() {
		return this.hashCode();
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
	
	

}
