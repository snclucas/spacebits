package org.spacebits.components.energygeneration;

import org.spacebits.data.DataFactory;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.physics.Unit;

public class PowerGenerationFactory extends DataFactory {

	public static PowerGenerator getPowerGenerator(String powerGenerationType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(powerGenerationType);
		
		if(powerGenerationType.equals(SimpleSolarArray.typeID.toString())){
			double arrayArea = 1.0* Unit.m * 15 * Unit.m;	
			
			double efficiency = 75 * Unit.percent;
			
			PowerGenerator solarArray = 
					new SimpleSolarArray("Simple Solar Array", data.getBusComponentSpecification(), arrayArea, efficiency);

			return solarArray;
		}
		else if(powerGenerationType.equals(SubspacePowerExtractor.typeID.toString())){
			double maximumPowerOutputFromEther = 100 * Unit.kW;
			
			PowerGenerator solarArray = 
					new SubspacePowerExtractor("Sub ether extractor", data.getBusComponentSpecification(), maximumPowerOutputFromEther);

			return solarArray;
		}

		return null;
	}

}
