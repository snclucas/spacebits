package org.braincycles.spacebits.components.computers;

import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.Spacecraft;

public class MockSystemComputer extends BasicSystemComputer{

	protected double totalPowerAvailable = 0.0;
	protected double totalCPUAvailable = 0.0;
	
	
	public MockSystemComputer(String name,
			BusComponentSpecification busResourceSpecification,
			double maxCPUThroughput, Spacecraft spacecraftBus) {
		super(name, busResourceSpecification, maxCPUThroughput, spacecraftBus);
	}

	@Override
	public double getTotalPowerAvailable() {
		return totalPowerAvailable;
	}

	@Override
	public double getTotalCPUThroughputAvailable() {
		return totalCPUAvailable;
	}

	
	
	
	
}
