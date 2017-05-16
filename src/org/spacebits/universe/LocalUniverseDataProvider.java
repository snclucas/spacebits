package org.spacebits.universe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.spacebits.components.TypeInfo;
import org.spacebits.physics.Unit;
import org.spacebits.universe.celestialobjects.CelestialObject;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;
import org.spacebits.universe.structures.SubspaceBeacon;
import org.spacebits.utils.Utils;

//XXX Make better

public class LocalUniverseDataProvider extends AbstractUniverseDataProvider implements UniverseDataProvider {

	Map<Integer, CelestialObject> galacticLocations;

	public LocalUniverseDataProvider() {
		super();
		galacticLocations = new HashMap<Integer, CelestialObject>();
		populate();
	}

	@Override
	public int addLocation(CelestialObject location) {
		return galacticLocations.put(location.getId(), location) != null ? 1:0;
	}




	@Override
	public CelestialObject getLocation(int locationID) {
		return galacticLocations.get(locationID);
	}
	

	@Override
	public CelestialObject getLocation(String locationProperName) {
		Iterator<Entry<Integer, CelestialObject>> it = galacticLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(locationProperName.equals(loc.getName()))
				return loc;
		}
		return null;
	}
	

	@Override
	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();

		Iterator<Entry<Integer, CelestialObject>> it = galacticLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(type == (((CelestialObject)loc).getCategoryId()))
				locations.add(loc);
		}
		return locations;
	}
	
	
	@Override
	public List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();

		Iterator<Entry<Integer, CelestialObject>> it = galacticLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(type == (((CelestialObject)loc).getTypeId()))
				if( Utils.distanceToLocation(loc.getCoordinates(), coordinates).compareTo(distance) <= 0)
					locations.add(loc);
		}
		return locations;
	}
	
	

	@Override
	public List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();
		Iterator<Entry<Integer, CelestialObject>> it = galacticLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if( Utils.distanceToLocation(loc.getCoordinates(), coordinates).compareTo(distance) <= 0)
				locations.add(loc);
		}
		return locations;
	}



	private void populate() {
		Star sol = new Star("Sol", new Coordinates(new BigDecimal(8*Unit.kPc),new BigDecimal(0),new BigDecimal(100*Unit.Ly)),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.G_CLASS_STAR));
		addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", 
				new Coordinates(new BigDecimal(8*Unit.kPc + 2.98*Unit.Ly),new BigDecimal(2.83* Unit.Ly),new BigDecimal(101.34*Unit.Ly)),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.O_CLASS_STAR));
		addLocation(alphaCenturi);
		
		
		
		//Setup subspace beacons
		
		//Above Sol north pole, 1e8 Km
		addLocation(new SubspaceBeacon("SolBeacon", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km)), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));
		
		addLocation(new SubspaceBeacon("ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km)), alphaCenturi,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));
	}



}
