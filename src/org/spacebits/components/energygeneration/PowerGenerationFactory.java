package org.spacebits.components.energygeneration;

import org.spacebits.components.TypeInfo;
import org.spacebits.data.DataFactory;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.physics.Unit;

public class PowerGenerationFactory extends DataFactory {

	public static PowerGenerator getPowerGenerator(TypeInfo powerGenerationType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(powerGenerationType);
		
		if(powerGenerationType.equals(SimpleSolarArray.type())){
			double arrayArea = 1.0* Unit.m * 15 * Unit.m;	
			
			double efficiency = 75 * Unit.percent;
			
			PowerGenerator solarArray = 
					new SimpleSolarArray("Simple Solar Array", data.getBusComponentSpecification(), arrayArea, efficiency);

			return solarArray;
		}
		else if(powerGenerationType.equals(SubspacePowerExtractor.type())){
			double maximumPowerOutputFromEther = 100 * Unit.kW;
			
			PowerGenerator solarArray = 
					new SubspacePowerExtractor("Sub ether extractor", data.getBusComponentSpecification(), maximumPowerOutputFromEther);

			return solarArray;
		}

		return null;
	}

}
