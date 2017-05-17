package org.spacebits.components.comms;

import org.spacebits.algorithm.Model;
import org.spacebits.algorithm.SimpleRadioFrequencyPropagationModel;
import org.spacebits.components.TypeInfo;
import org.spacebits.data.DataFactory;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.physics.Physics;
import org.spacebits.physics.Unit;


public class CommunicatorDeviceFactory extends DataFactory {

	public static CommunicationComponent getCommunicator(TypeInfo commType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(commType);
		
		if(commType.equals(RadioCommunicator.type())) {
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("RF");
			CommunicationComponent communicationComponent = 
					new RadioCommunicator(RadioCommunicator.type().toString(), data.getBusComponentSpecification(), propagationModel);
			communicationComponent.setDeviceNoiseLevel(Physics.dBm2W(-80)); // -80 dBm
			communicationComponent.setEfficiency(90 * Unit.percent.value());
			return communicationComponent;
		}
		else if(commType.equals(SubSpaceCommunicator.type())){
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("SUBSPACE");
			CommunicationComponent communicationComponent = 
					new SubSpaceCommunicator(SubSpaceCommunicator.type().toString(), data.getBusComponentSpecification(), propagationModel);
			return communicationComponent;
		}
		return null;
	}
	

}
