package org.spacebits.profiles;

public abstract class AbstractProfile {

	private String name;
	
	public AbstractProfile(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
