package org.spacebits.components.computers;

import java.util.List;
import java.util.Map;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	void saveData(DataRecord data);
	DataRecord getData(String id, TypeInfo typeInfo);
	Map<String, DataRecord> getData(TypeInfo typeInfo);
	
	public Map<String,DataRecord> getData(TypeInfo category, TypeInfo ... subType);
	
	void saveData(Identifiable ... data);
	void saveData(List<? extends Identifiable> data);
}
