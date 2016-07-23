package org.braincycles.spacebits.components.energygeneration;

import org.braincycles.spacebits.components.AbstractBusComponent;
import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractPowerGenerator extends AbstractBusComponent implements PowerGenerator {

	
	public AbstractPowerGenerator(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
		
	}


	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
}
