package org.braincycles.spacebits.components.comms;

import org.braincycles.spacebits.algorithm.Model;
import org.braincycles.spacebits.algorithm.SimpleRadioFrequencyPropagationModel;
import org.braincycles.spacebits.data.DataFactory;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.physics.Physics;
import org.braincycles.spacebits.physics.Unit;


public class CommunicatorDeviceFactory extends DataFactory {

	public static CommunicationComponent getCommunicator(String commType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(commType);
		
		if(commType.equalsIgnoreCase(RadioCommunicator.typeID.toString())){
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("RF");
			CommunicationComponent communicationComponent = 
					new RadioCommunicator(RadioCommunicator.typeID.toString(), data.getBusComponentSpecification(), propagationModel);
			communicationComponent.setDeviceNoiseLevel(Physics.dBm2W(-80)); // -80 dBm
			communicationComponent.setEfficiency(90 * Unit.percent);
			return communicationComponent;
		}
		else if(commType.equalsIgnoreCase(SubSpaceCommunicator.typeID.toString())){
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("SUBSPACE");
			CommunicationComponent communicationComponent = 
					new SubSpaceCommunicator(SubSpaceCommunicator.typeID.toString(), data.getBusComponentSpecification(), propagationModel);
			return communicationComponent;
		}
		return null;
	}
	

}
