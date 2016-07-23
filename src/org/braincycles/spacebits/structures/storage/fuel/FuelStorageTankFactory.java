package org.braincycles.spacebits.structures.storage.fuel;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.data.SpacecraftComponentData;
import org.braincycles.spacebits.data.SpacecraftDataProvider;
import org.braincycles.spacebits.exceptions.ItemNotFoundException;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;

public class FuelStorageTankFactory {
	
	public static FuelStorageTank getFuelStorageTank(String tankType, double capacity){
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(tankType);

		if(tankType.equals(LiquidStorageTank.typeID.toString())){	
			double tankMassOverheadFraction = 1.1;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new LiquidStorageTank(tankType, busSpecs, capacity);
		}
		else if(tankType.equals(CryogenicLiquidStorageTank.typeID.toString())){
			double tankMassOverheadFraction = 1.3;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new CryogenicLiquidStorageTank(tankType, busSpecs, capacity);
		}
		else {
			throw new ItemNotFoundException("No fuel tank found with type: " + tankType);
		}
		
	}

}
