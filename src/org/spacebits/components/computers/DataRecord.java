package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public class DataRecord {
	
	private String recordTag;
	private TypeInfo recordType;
	private ArchivableData<?> data;
	
	public DataRecord(String recordTag, ArchivableData<?> data) {
		super();
		this.recordTag = recordTag;
		this.recordType = data.getTypeId();
		this.data = data;
	}
	
	public TypeInfo getRecordType() {
		return recordType;
	}

	public int getRecordID() {
		return hashCode();
	}

	public String getRecordTag() {
		return recordTag;
	}

	public ArchivableData<?> getData() {
		return data;
	}

}
