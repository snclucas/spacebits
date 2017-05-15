package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SignalResponse;
import org.spacebits.exceptions.ComponentConfigurationException;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.Region;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.universe.structures.SubspaceBeacon;
import org.spacebits.utils.Utils;

public class Universe {

	private UniverseDataProvider dataProvider;

	private static Map<String,Spacecraft> spacecraftInUniverse = new HashMap<String, Spacecraft>();

	private static Map<String,Coordinates> spacecraftLocationInUniverse = new HashMap<String, Coordinates>();

	public final static CelestialObject galacticCenter 
	= new Region(0, "Galactic center", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)),
			new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc);

	public static double SUBSPACE_SIGNAL_PROPAGATION = 100000*Unit.c;



	public Universe(UniverseDataProvider dataProvider) {
		super();
		this.dataProvider = dataProvider;
		//this.spacecraftInUniverse = new HashMap<Integer, Spacecraft>();
		//this.spacecraftLocationInUniverse = new HashMap<Integer, Location>();
	}


	public static Universe getUniverse() {
		return setupSimpleUniverse();
	}



	public static void addSpacecraft(Spacecraft spacecraft) {
		spacecraftInUniverse.put(spacecraft.getIdent(), spacecraft);
	}

	public static void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		spacecraftLocationInUniverse.put(spacecraftIdent, coordinates);
	}

	public static Coordinates getSpacecraftLocation(String spacecraftIdent) {
		if(spacecraftLocationInUniverse.get(spacecraftIdent) == null)
			throw new ComponentConfigurationException("Spacecraft location has not been set");
		return spacecraftLocationInUniverse.get(spacecraftIdent);
	}


	public EnvironmentData getEnvironmentData(String spacecraftIdent) {
		Coordinates coordinates = getSpacecraftLocation(spacecraftIdent);

		List<CelestialObject> nearByStars = //Stars within 100 AU
				dataProvider.getLocationsByTypeCloserThan(Star.typeID, coordinates, new BigDecimal(100 * 1.496e8 * Unit.Km));

		if(nearByStars.size() == 0)
			return new EnvironmentData(0.0, 0.0);

		double luminosity = 0.0;
		for(CelestialObject celestial : nearByStars) {
			if(celestial instanceof Star) {
				Star star = ((Star)celestial);
				BigDecimal d = Utils.distanceToLocation(coordinates, star.getCoordinates());
				SignalResponse response = star.getSensorSignalResponse().getSignalResponse(Sensor.OPTICAL, BigDecimal.ZERO);
				d = d.max(new BigDecimal(Unit.G_STAR_RADIUS));
				luminosity += response.getSignalStrength() / (4*Math.PI* (d.pow(2)).doubleValue() );
			}
		} 

		return new EnvironmentData(luminosity, 0.0);
	}


	private static Universe setupSimpleUniverse() {

		UniverseDataProvider dataProvider = new LocalUniverseDataProvider();
		Universe universe = new Universe(dataProvider);

		Star sol = new Star(1,"Sol", new Coordinates(new BigDecimal(8*Unit.kPc),new BigDecimal(0),new BigDecimal(100*Unit.Ly)),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.G_CLASS_STAR));
		universe.addLocation(sol);

		Star alphaCenturi = new Star(2,"Alpha centuri", 
				new Coordinates(new BigDecimal(8*Unit.kPc + 2.98*Unit.Ly),new BigDecimal(2.83* Unit.Ly),new BigDecimal(101.34*Unit.Ly)),
				new SensorSignalResponseProfile(1.0, 1.0, 1.0, 1.0, 1.0));
		universe.addLocation(alphaCenturi);



		//Setup subspace beacons

		//Above Sol north pole, 1e8 Km
		universe.addLocation(new SubspaceBeacon(3,"SolBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km)), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));


		universe.addLocation(new SubspaceBeacon(4,"ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km)), alphaCenturi,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));

		return universe;
	}







	public UniverseDataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(UniverseDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}


	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		return dataProvider.getLocationsByType(type);
	}


	public int addLocation(CelestialObject location) {
		return dataProvider.addLocation(location);
	}

	public CelestialObject getLocation(int locationID) {
		return dataProvider.getLocation(locationID);
	}

	public CelestialObject getLocation(String locationProperName) {
		return dataProvider.getLocation(locationProperName);
	}



	public long getUniversalTime() {
		return System.currentTimeMillis();
	}
	
	
	public double[] moveSpacecraft() {
		double[] thrust = new double[]{};
		for(Spacecraft spacecraft : spacecraftInUniverse.values()) {
			//thrust = spacecraft.getThrust();
		}
		return thrust;
	}




}
