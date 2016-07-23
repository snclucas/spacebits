package org.braincycles.spacebits.algorithm.thrust;

import org.braincycles.spacebits.components.TypeInfo;

public abstract class AbstractThrustAlgorithm implements ThrustAlgorithm {

	private String name;
	
	public AbstractThrustAlgorithm(String name) {
		this.name = name;
	}
	
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	

	@Override
	public int getId() {
		return this.hashCode();
	}


	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
}
