package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	void saveData(DataRecord data);
	DataRecord getData(String id, TypeInfo typeInfo);
	Archive getData(TypeInfo typeInfo);
}
