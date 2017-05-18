package org.spacebits.components.energygeneration;

import org.spacebits.components.Component;
import org.spacebits.components.TypeInfo;

public interface PowerGenerator extends Component{
	
	public static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	public double getPowerOutput();
	public double getMaximumPowerOutput();

}
