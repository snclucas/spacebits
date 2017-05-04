package org.spacebits.components.computers;

import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.TypeInfo;

public class SpacecraftData implements ArchivableData {

	private final String dataName;
	private final Map<String, Object> data = new HashMap<String, Object>();
	
	public SpacecraftData(String dataName) {
		super();
		this.dataName = dataName;
	}
	

	public String getDataName() {
		return dataName;
	}

	
	public Map<String, Object> getData() {
		return data;
	}


	@Override
	public Object getData(String id) {
		return data.get(id);
	}

	@Override
	public void setData(String id, Object data) {
		this.data.put(id, data);
	}


	@Override
	public TypeInfo getTypeId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TypeInfo getCategoryId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public DataRecord getDataRecord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
