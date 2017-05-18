package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.components.TypeInfo;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.Region;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.universe.structures.SubspaceBeacon;

public class Universe {
	
	private UniverseLocationDataProvider universeLocationDataProvider = Configuration.getUniverseLocationDataProvider();
	private UniverseSpacecraftLocationDataProvider universeSpacecraftLocationDataProvider = Configuration.getUniverseSpacecraftLocationDataProvider();
	

	public final static CelestialObject galacticCenter 
	= new Region("Galactic center", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)),
			new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());


	public Universe(UniverseLocationDataProvider dataProvider) {
		super();
		this.universeLocationDataProvider = dataProvider;
	}


	public List<CelestialObject> getCelelestialObjects() {
		return universeLocationDataProvider.getLocationsByCategory(CelestialObject.category());
	}


	public void addSpacecraft(Spacecraft spacecraft) {
		universeSpacecraftLocationDataProvider.addSpacecraft(spacecraft);
	}

	
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		universeSpacecraftLocationDataProvider.updateSpacecraftLocation(spacecraftIdent, coordinates);
	}
	
	
	public void updateSpacecraftLocation(String spacecraftIdent, Location location) {
		universeSpacecraftLocationDataProvider.updateSpacecraftLocation(spacecraftIdent, location.getCoordinates());
	}

	
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		return universeSpacecraftLocationDataProvider.getSpacecraftLocation(spacecraftIdent);
	}




	public void setupSimpleUniverse() {
System.out.println("Adding ");
		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(
				new BigDecimal(8*Unit.kPc.value()),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,  
				new Coordinates(
						new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
						new BigDecimal(2.83* Unit.Ly.value()),
						new BigDecimal(101.34*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.M_CLASS_STAR));
		addLocation(alphaCenturi);



		//Setup subspace beacons

		//Above Sol north pole, 1e8 Km
		addLocation(new SubspaceBeacon("SolBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));


		addLocation(new SubspaceBeacon("ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), alphaCenturi,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));

	}







	public UniverseLocationDataProvider getDataProvider() {
		return universeLocationDataProvider;
	}

	public void setDataProvider(UniverseLocationDataProvider dataProvider) {
		this.universeLocationDataProvider = dataProvider;
	}


	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		return universeLocationDataProvider.getLocationsByType(type);
	}


	public int addLocation(CelestialObject location) {
		return universeLocationDataProvider.addLocation(location);
	}

	public CelestialObject getLocationById(String locationID) {
		return universeLocationDataProvider.getLocationById(locationID);
	}

	public CelestialObject getLocationByName(String locationProperName) {
		return universeLocationDataProvider.getLocationByName(locationProperName);
	}



	public long getUniversalTime() {
		return System.currentTimeMillis();
	}
	
	
	public double[] moveSpacecraft() {
		double[] thrust = new double[]{};
		//for(Spacecraft spacecraft : spacecraftInUniverse.values()) {
			//thrust = spacecraft.getThrust();
		//}
		return thrust;
	}




}
