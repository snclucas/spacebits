import java.math.BigDecimal;
import java.util.List;

import org.braincycles.spacebits.components.sensors.Sensor;
import org.braincycles.spacebits.components.sensors.SensorProfile;
import org.braincycles.spacebits.components.sensors.SensorResult;
import org.braincycles.spacebits.physics.Physics;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.spacecraft.SpacecraftFactory;
import org.braincycles.spacebits.status.SystemStatus;
import org.braincycles.spacebits.universe.Coordinates;
import org.braincycles.spacebits.universe.Universe;


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
