package org.spacebits.components.computers;

import java.util.List;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	void saveData(DataRecord data);
	DataRecord getData(String id, TypeInfo typeInfo);
	Archive getData(TypeInfo typeInfo);
	
	void saveData(Identifiable ... data);
	void saveData(List<? extends Identifiable> data);
}
