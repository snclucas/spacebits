package org.braincycles.spacebits.software;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.computers.SystemComputer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	protected SystemComputer computer;

	
	public AbstractSoftware(String name) {
		this.name = name;
	}
	
	public AbstractSoftware(String name, SystemComputer computer) {
		this.computer = computer;
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
