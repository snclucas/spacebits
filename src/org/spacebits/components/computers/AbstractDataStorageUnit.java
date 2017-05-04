package org.spacebits.components.computers;

import java.util.HashMap;
import java.util.Map;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;


public abstract class AbstractDataStorageUnit extends AbstractBusComponent implements DataStore {
	
	public AbstractDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}


	private final Map<String, ArchivableData> data = new HashMap<String, ArchivableData>();

	
	

	@Override
	public ArchivableData saveData(String id, ArchivableData dataRecord) {
		return data.put(id, dataRecord);
	}


	@Override
	public ArchivableData getData(String id) {
		return data.get(id);
	}


	@Override
	public double getOperatingPower() {
		// Power remains constant
		return getNominalPower();
	}


	@Override
	public double getOperatingCPUThroughput() {
		return getNominalCPUThroughput();
	}


	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}

	

}
