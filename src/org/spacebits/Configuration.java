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
import org.spacebits.universe.LocalUniverseDataProvider;
import org.spacebits.universe.Universe;
import org.spacebits.universe.UniverseDataProvider;

public class Configuration {

	public static int precision = 20;
	public static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
	public static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	public static double distanceForEnvironmentData = 100.0 * Unit.Ly;
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
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
