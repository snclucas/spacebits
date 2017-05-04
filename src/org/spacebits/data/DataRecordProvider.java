package org.spacebits.data;

import java.util.List;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.DataRecord;

public interface DataRecordProvider {
	DataRecord getDataRecordByID(int id);
	DataRecord addDataRecord(DataRecord dataRecord);
	DataRecord overwriteDataRecordWithId(int id, DataRecord dataRecord);
	List<DataRecord> getDataRecordsByType(TypeInfo type);
	DataRecord deleteDataRecordByID(int id);
}