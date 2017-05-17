package org.spacebits.universe;

import java.math.BigDecimal;

import org.spacebits.physics.Unit;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.celestialobjects.Star;

public class UniversePopulator {

	public UniversePopulator() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void populate(Universe universe) {
		
		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		universe.addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,  
				new Coordinates(new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),new BigDecimal(2.83* Unit.Ly.value()),new BigDecimal(101.34*Unit.Ly.value())),
				new SensorSignalResponseProfile(1.0, 1.0, 1.0, 1.0, 1.0));
		universe.addLocation(alphaCenturi);
		
		
		
		
		
	}
	

}
