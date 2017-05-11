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




	@Override
	public double getCurrentPower() {
		// Power remains constant
		return getNominalPower();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return getNominalCPUThroughput();
	}


	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}

	

}
