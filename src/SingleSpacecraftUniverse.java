import java.math.BigDecimal;
import java.util.List;

import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorProfile;
import org.spacebits.components.sensors.SensorResult;
import org.spacebits.physics.Physics;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.status.SystemStatus;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;


public class SingleSpacecraftUniverse {

	public SingleSpacecraftUniverse() { 
		
		Universe universe = Universe.getUniverse();
		Spacecraft spacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		Universe.addSpacecraft(spacecraft);
		Universe.updateSpacecraftLocation(spacecraft.getId(), 
				new Coordinates(new BigDecimal(8*Unit.kPc), new BigDecimal(0.0), new BigDecimal(0.0)));
		
		
		
		universe.moveSpacecraft();
		
		run();	
	}

	private void run() {




	}

	public static void main(String[] args) {
		
		new SingleSpacecraftUniverse() ;
	}

}