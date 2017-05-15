

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
import org.spacebits.universe.Universe;


public class Driver {
	
	
	public Driver() {
		
		
		
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		Universe.addSpacecraft(simpleSpacecraft);
		
		Coordinates spacecraftLocation = new Coordinates(new BigDecimal(8*Unit.kPc + 149600000 * Unit.Km),new BigDecimal(0),new BigDecimal(100*Unit.Ly));
		Universe.updateSpacecraftLocation(simpleSpacecraft.getIdent(), spacecraftLocation);
		
		Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type.toString(), Sensor.RADAR, 1);
		simpleSpacecraft.addComponent(sensor);
		
		simpleSpacecraft.online();
		
		List<SensorResult> results = sensor.passiveScan(10, sensor.getSensorProfile());

		
	
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}
