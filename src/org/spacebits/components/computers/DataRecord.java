package org.spacebits.components.computers;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public class DataRecord {
	
	private final String id;
	private final TypeInfo type;
	private final TypeInfo category;
	private final Object data;
	
	
	public DataRecord() {
		super();
		this.id = "";
		this.type = null;
		this.category = null;
		this.data = null;
	}
	
	public DataRecord(String id, Identifiable data) {
		super();
		this.id = id;
		this.type = data.getType();
		this.category = data.getCategory();
		this.data = data;
	}
	
	public TypeInfo getRecordType() {
		return type;
	}
	
	public TypeInfo getRecordCategory() {
		return category;
	}

	public String getRecordID() {
		return id;
	}

	public Object getData() {
		return data;
	}
	
	public boolean hasData() {
		return data != null;
	}

}
