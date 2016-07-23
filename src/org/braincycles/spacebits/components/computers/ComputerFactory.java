package org.braincycles.spacebits.components.computers;

import org.braincycles.spacebits.data.DataFactory;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.exceptions.ItemNotFoundException;
import org.braincycles.spacebits.software.PropulsionManagementSoftware;
import org.braincycles.spacebits.software.MessageMediator;
import org.braincycles.spacebits.software.Software;
import org.braincycles.spacebits.software.SystemMessageService;

public class ComputerFactory extends DataFactory {

	public static SystemComputer getComputer(String computerType){
		
		if(computerType.equals(BasicSystemComputer.typeID.toString())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(computerType);

			double maxCPUThroughput = 1000; //GFLOP
			
			// Set spacecraft bus to null
			SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), maxCPUThroughput, null);
			
			Software engineSoftware = new PropulsionManagementSoftware("Engine software", computer);
			computer.loadSoftware(engineSoftware);
			
			MessageMediator messagingSystem = new SystemMessageService();
			computer.setMessagingSystem(messagingSystem);
			return computer;
		}

		throw new ItemNotFoundException("No computer found with type: " + computerType);
	}

}
