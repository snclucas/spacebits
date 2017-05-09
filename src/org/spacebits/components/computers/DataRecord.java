package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;

public class DataRecord {
	
	private final String recordTag;
	private final TypeInfo recordType;
	private final ArchivableData data;
	
	
	public DataRecord() {
		super();
		this.recordTag = "";
		this.recordType = null;
		this.data = null;
	}
	
	public DataRecord(String recordTag, ArchivableData data) {
		super();
		this.recordTag = recordTag;
		this.recordType = data.getCategoryId();
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

	public ArchivableData getData() {
		return data;
	}
	
	public boolean hasData() {
		return data != null;
	}

}
