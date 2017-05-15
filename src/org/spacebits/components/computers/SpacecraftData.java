package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public class SpacecraftData implements ArchivableData {
	private TypeInfo type = new TypeInfo("SpacecraftData");
	private final String dataName;
	private final String data;
	
	public SpacecraftData(String dataName, String data) {
		super();
		this.dataName = dataName;
		this.data = data;
	}
	

	public String getDataName() {
		return dataName;
	}

	@Override
	public String getData() {
		return data;
	}


	@Override
	public TypeInfo getTypeId() {
		return type;
	}


	@Override
	public TypeInfo getCategoryId() {
		return category;
	}


	@Override
	public String getName() {
		return this.dataName;
	}


	@Override
	public int getId() {
		return this.hashCode();
	}


	@Override
	public String describe() {
		return "Data record to hold spacecraft data.";
	}


}
