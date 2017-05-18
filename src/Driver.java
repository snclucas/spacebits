

import java.math.BigDecimal;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.components.sensors.LinearSensorArray;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorFactory;
import org.spacebits.components.sensors.SensorResult;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Location;
import org.spacebits.universe.SimpleLocation;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;


public class Driver {
	
	
	public Driver() {
		Universe universe = Configuration.getUniverse();
		
		Location sol = new SimpleLocation("Sol", new Coordinates(
				new BigDecimal(8*Unit.kPc.value()),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly.value())));
		
		Location initialSpacecraftLocation = new SimpleLocation("Spacecraft", new Coordinates(
				new BigDecimal(0),
				new BigDecimal(0),
				new BigDecimal(-10000*Unit.AU.value())), sol);
		
		
		System.out.println(sol);
		System.out.println(initialSpacecraftLocation);
		System.out.println(sol.vectorToLocation(initialSpacecraftLocation, true));
		System.out.println(sol.distanceToLocation(initialSpacecraftLocation, Unit.AU));
		System.out.println("----------------------------------------");
		
		
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		universe.addSpacecraft(simpleSpacecraft);
		
		universe.updateSpacecraftLocation(simpleSpacecraft.getIdent(), initialSpacecraftLocation);
		
		Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type(), Sensor.RADAR, 1);
		simpleSpacecraft.addComponent(sensor);
		
		simpleSpacecraft.online();
		
		List<SensorResult> results = sensor.passiveScan(10, sensor.getSensorProfile());

		for(SensorResult result : results) {
			System.out.println(result.getNavigationVector());
		}
		
	
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}
