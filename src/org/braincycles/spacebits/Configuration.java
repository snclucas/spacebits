package org.braincycles.spacebits;

import java.math.BigDecimal;

import org.braincycles.spacebits.components.sensors.LocalSensorResponseMediator;
import org.braincycles.spacebits.components.sensors.SensorResponseMediator;
import org.braincycles.spacebits.data.EnvironmentDataProvider;
import org.braincycles.spacebits.data.LocalEnvironmentDataProvider;
import org.braincycles.spacebits.data.LocalMaterialDataProvider;
import org.braincycles.spacebits.data.LocalPhysicsDataProvider;
import org.braincycles.spacebits.data.LocalSpacecraftDataProvider;
import org.braincycles.spacebits.data.MaterialDataProvider;
import org.braincycles.spacebits.data.PhysicsDataProvider;
import org.braincycles.spacebits.data.SpacecraftDataProvider;
import org.braincycles.spacebits.universe.LocalUniverseDataProvider;
import org.braincycles.spacebits.universe.Universe;
import org.braincycles.spacebits.universe.UniverseDataProvider;

public class Configuration {

	public static int precision = 100;
	public static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
	
	
	public static Universe getUniverse() {
		return Universe.getUniverse();
	}
	
	
	public static UniverseDataProvider getUniverseDataProvider() {
		return new LocalUniverseDataProvider();
	}
	
	
	public static EnvironmentDataProvider getEnvironmentDataProvider() {
		return new LocalEnvironmentDataProvider();
	}
	
	public static SensorResponseMediator getSensorResponseMediator() {
		return new LocalSensorResponseMediator();
	}
	
	
	public static SpacecraftDataProvider getSpacecraftDataProvider() {
		return new LocalSpacecraftDataProvider();
	}
	
	
	public static PhysicsDataProvider getPhysicsDataProvider() {
		return new LocalPhysicsDataProvider();
	}
	

	public static MaterialDataProvider getMaterialDataProvider() {
		return new LocalMaterialDataProvider();
	}

}
