package org.spacebits.components.energygeneration;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractPowerGenerator extends AbstractBusComponent implements PowerGenerator {

	
	public AbstractPowerGenerator(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
		
	}


	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
}
