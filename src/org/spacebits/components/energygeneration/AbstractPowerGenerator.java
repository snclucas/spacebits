package org.spacebits.components.energygeneration;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.spacecraft.BusComponentSpecification;

public abstract class AbstractPowerGenerator extends AbstractBusComponent implements PowerGenerator {


	protected double maxPower;
	
	public static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	
	public static TypeInfo type() {
		return new TypeInfo("PowerGenerator");
	}
	
	public AbstractPowerGenerator(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}


	@Override
	public final TypeInfo getCategory() {
		return category();
	}
	
	
	
	
	@Override
	public double getMaximumPowerOutput() {
		return maxPower;
	}
	
}
