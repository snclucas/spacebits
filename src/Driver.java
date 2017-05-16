

import java.math.BigDecimal;
import java.util.List;

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
		
		Location sol = new SimpleLocation("Sol", new Coordinates(
				new BigDecimal(8*Unit.kPc),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly)));
		
		Location initialSpacecraftLocation = new SimpleLocation("Spacecraft", new Coordinates(
				new BigDecimal(0),
				new BigDecimal(0),
				new BigDecimal(-10000*Unit.AU)), sol);
		
		
		System.out.println(sol);
		System.out.println(initialSpacecraftLocation);
		
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		Universe.addSpacecraft(simpleSpacecraft);
		
		Universe.updateSpacecraftLocation(simpleSpacecraft.getIdent(), initialSpacecraftLocation);
		
		Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type.toString(), Sensor.RADAR, 1);
		simpleSpacecraft.addComponent(sensor);
		
		simpleSpacecraft.online();
		
		List<SensorResult> results = sensor.passiveScan(10, sensor.getSensorProfile());

		
	
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}
