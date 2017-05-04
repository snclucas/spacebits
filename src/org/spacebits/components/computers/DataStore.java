package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	ArchivableData saveData(String id, ArchivableData dataRecord);
	
	ArchivableData getData(String id);
	
}
