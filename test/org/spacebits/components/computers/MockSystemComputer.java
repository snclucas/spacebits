package org.spacebits.components.computers;

import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.spacecraft.Bus;
import org.spacebits.spacecraft.BusComponentSpecification;


public class MockSystemComputer extends BasicSystemComputer{

	protected double totalPowerAvailable = 0.0;
	protected double totalCPUAvailable = 0.0;
	
	
	public MockSystemComputer(String name,
			BusComponentSpecification busResourceSpecification,
			double maxCPUThroughput, Bus spacecraftBus) {
		super(name, busResourceSpecification, maxCPUThroughput);
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
