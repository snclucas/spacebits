package org.spacebits.components.computers;

import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.TypeInfo;

public class SpacecraftData implements ArchivableData {
	private TypeInfo typeID = new TypeInfo("SpacecraftData");
	private final String dataName;
	private final Map<String, Double> data = new HashMap<String, Double>();
	
	public SpacecraftData(String dataName) {
		super();
		this.dataName = dataName;
	}
	

	public String getDataName() {
		return dataName;
	}

	
	public Map<String, Double> getData() {
		return data;
	}


	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	@Override
	public TypeInfo getCategoryId() {
		return categoryID;
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
