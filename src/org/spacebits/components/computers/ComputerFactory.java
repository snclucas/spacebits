package org.spacebits.components.computers;

import org.spacebits.data.DataFactory;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.exceptions.ItemNotFoundException;
import org.spacebits.software.MessageMediator;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.software.Software;
import org.spacebits.software.SystemMessageService;

public class ComputerFactory extends DataFactory {

	public static SystemComputer getComputer(String computerType){
		
		if(computerType.equals(BasicSystemComputer.typeID.toString())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(computerType);

			double maxCPUThroughput = 1000; //GFLOP
			
			// Set spacecraft bus to null
			SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), maxCPUThroughput);
			
			Software engineSoftware = new PropulsionManagementSoftware("Engine software", computer);
			computer.loadSoftware(engineSoftware);
			
			MessageMediator messagingSystem = new SystemMessageService("System message mediator software", computer);
			computer.loadSoftware(messagingSystem);
			return computer;
		}

		throw new ItemNotFoundException("No computer found with type: " + computerType);
	}

}
