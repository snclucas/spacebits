package org.braincycles.spacebits.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.components.computers.DataRecord;

public class LocalTransientDataRecordProvider implements DataRecordProvider {
	
	private Map<Integer, DataRecord> dataRecords;
	
	
	public LocalTransientDataRecordProvider() {
		this.dataRecords = new HashMap<Integer, DataRecord>();
	}

	@Override
	public DataRecord getDataRecordByID(int id) {
		DataRecord returnedRecord = dataRecords.get(id);
		if(returnedRecord == null)
			return new DataRecord();
		return null;
	}

	@Override
	public DataRecord addDataRecord(DataRecord dataRecord) {
		if(dataRecords.containsValue(dataRecord) == false)
			return dataRecords.put(dataRecord.getRecordID(), dataRecord);
		return getDataRecordByID(dataRecord.getRecordID());
	}
	
	@Override
	public DataRecord overwriteDataRecordWithId(int id, DataRecord dataRecord) {
		if(dataRecords.containsValue(dataRecord) == true)
			return dataRecords.put(dataRecord.getRecordID(), dataRecord);
		return new DataRecord();
	}

	@Override
	public List<DataRecord> getDataRecordsByType(TypeInfo type) {
		List<DataRecord> records = new ArrayList<DataRecord>();
		Iterator<Entry<Integer, DataRecord>> it = dataRecords.entrySet().iterator();
		while (it.hasNext()) {
			DataRecord record = it.next().getValue();
			if(type == record.getRecordType())
				records.add(record);
		}
		return records;
	}

	@Override
	public DataRecord deleteDataRecordByID(int id) {
		DataRecord recordToDelete = getDataRecordByID(id);
		if(recordToDelete.isEmpty() == false)
			return dataRecords.remove(id);
		else
			return new DataRecord();
	}

}
