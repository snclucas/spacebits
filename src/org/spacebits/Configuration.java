package org.spacebits;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

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
import org.spacebits.physics.Unit;
import org.spacebits.universe.LocalUniverseLocationDataProvider;
import org.spacebits.universe.LocalUniverseSpacecraftLocationDataProvider;
import org.spacebits.universe.UniverseLocationDataProvider;
import org.spacebits.universe.UniverseSpacecraftLocationDataProvider;

public class Configuration {

	public static int precision = 20;
	public static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
	public static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	public static double distanceForEnvironmentData = 100.0 * Unit.Ly.value();

	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	
	public static UniverseLocationDataProvider getUniverseLocationDataProvider() {
		return new LocalUniverseLocationDataProvider();
	}
	
	
	public static UniverseSpacecraftLocationDataProvider getUniverseSpacecraftLocationDataProvider() {
		return new LocalUniverseSpacecraftLocationDataProvider();
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
