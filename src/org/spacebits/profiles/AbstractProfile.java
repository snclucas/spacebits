package org.spacebits.profiles;

import org.spacebits.Configuration;
import org.spacebits.components.Identifiable;

public abstract class AbstractProfile implements Identifiable {

	private String name;
	private final String ident;
	
	public AbstractProfile(String name) {
		this.name = name;
		this.ident = Configuration.getUUID();
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getIdent() {
		return this.ident;
	}
	
}
