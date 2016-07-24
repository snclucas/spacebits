package org.spacebits;

import java.math.BigDecimal;

import org.spacebits.components.sensors.LocalSensorResponseMediator;
import org.spacebits.components.sensors.SensorResponseMediator;
import org.spacebits.data.EnvironmentDataProvider;
import org.spacebits.data.LocalEnvironmentDataProvider;
import org.spacebits.data.LocalMaterialDataProvider;
import org.spacebits.data.LocalPhysicsDataProvider;
import org.spacebits.data.LocalSpacecraftDataProvider;
import org.spacebits.data.MaterialDataProvider;
import org.spacebits.data.PhysicsDataProvider;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.universe.LocalUniverseDataProvider;
import org.spacebits.universe.Universe;
import org.spacebits.universe.UniverseDataProvider;

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
