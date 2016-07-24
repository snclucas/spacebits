package org.spacebits.components.computers;

import org.spacebits.components.TypeInfo;



public class DataRecord {
	
	private int recordID;
	private TypeInfo recordType;
	private ArchivableData data;
	
	
	public DataRecord() {
		super();
		this.recordID = -1;
		this.recordType = null;
		this.data = null;
	}
	
	
	public DataRecord(int recordID, ArchivableData data) {
		super();
		this.recordID = recordID;
		this.recordType = data.getCategoryId();
		this.data = data;
	}
	
	
	public boolean isEmpty() {
		return recordID < 0;
	}
	
	
	public TypeInfo getRecordType() {
		return recordType;
	}


	public int getRecordID() {
		return recordID;
	}



	public ArchivableData getData() {
		return data;
	}


}
