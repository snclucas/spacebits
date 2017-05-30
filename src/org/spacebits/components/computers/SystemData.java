package org.spacebits.components.computers;

import org.spacebits.Configuration;
import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public class SystemData implements Identifiable {
	
	private final String tag;
	private final String data;
	
	
	public SystemData(String tag, String data) {
		super();
		this.tag = tag;
		this.data = data;
	}

	@Override
	public TypeInfo getType() {
		return new TypeInfo("SystemData");
	}

	@Override
	public TypeInfo getCategory() {
		return new TypeInfo("Data");
	}

	@Override
	public String getName() {
		return "SystemData";
	}

	@Override
	public String getIdent() {
		return Configuration.getUUID();
	}

	@Override
	public String describe() {
		return "SystemData";
	}

}
