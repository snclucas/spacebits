package org.spacebits.structures.storage.fuel;

import org.spacebits.Configuration;
import org.spacebits.data.SpacecraftComponentData;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.exceptions.ItemNotFoundException;
import org.spacebits.spacecraft.BusComponentSpecification;

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
