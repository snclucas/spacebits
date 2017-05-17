package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacebits.components.TypeInfo;
import org.spacebits.exceptions.ComponentConfigurationException;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.Region;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.universe.structures.SubspaceBeacon;

public class Universe {
	
	private UniverseDataProvider dataProvider;

	private static Map<String,Spacecraft> spacecraftInUniverse = new HashMap<String, Spacecraft>();

	private static Map<String,Coordinates> spacecraftLocationInUniverse = new HashMap<String, Coordinates>();

	public final static CelestialObject galacticCenter 
	= new Region("Galactic center", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)),
			new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());


	public Universe(UniverseDataProvider dataProvider) {
		super();
		this.dataProvider = dataProvider;
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
	
	
	public static void updateSpacecraftLocation(String spacecraftIdent, Location location) {
		spacecraftLocationInUniverse.put(spacecraftIdent, location.getCoordinates());
	}

	
	public static Coordinates getSpacecraftLocation(String spacecraftIdent) {
		if(spacecraftLocationInUniverse.get(spacecraftIdent) == null)
			throw new ComponentConfigurationException("Spacecraft location has not been set");
		return spacecraftLocationInUniverse.get(spacecraftIdent);
	}




	private static Universe setupSimpleUniverse() {

		UniverseDataProvider dataProvider = new LocalUniverseDataProvider();
		Universe universe = new Universe(dataProvider);

		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(
				new BigDecimal(8*Unit.kPc.value()),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		universe.addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,  
				new Coordinates(
						new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
						new BigDecimal(2.83* Unit.Ly.value()),
						new BigDecimal(101.34*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.M_CLASS_STAR));
		universe.addLocation(alphaCenturi);



		//Setup subspace beacons

		//Above Sol north pole, 1e8 Km
		universe.addLocation(new SubspaceBeacon("SolBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));


		universe.addLocation(new SubspaceBeacon("ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), alphaCenturi,
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

	public CelestialObject getLocationById(String locationID) {
		return dataProvider.getLocationById(locationID);
	}

	public CelestialObject getLocationByName(String locationProperName) {
		return dataProvider.getLocationByName(locationProperName);
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
