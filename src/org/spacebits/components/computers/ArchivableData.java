package org.spacebits.components.computers;

import org.spacebits.components.Identifiable;

public interface ArchivableData<T> extends Identifiable {
	//void setData(String id, Object data);
	T getData(String id);
	//List<String> getDataIds();
	//DataRecord getDataRecord();
}
