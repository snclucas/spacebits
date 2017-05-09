package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	void saveData(String id, ArchivableData data);
	ArchivableData getData(String id, TypeInfo typeInfo);
	
}
