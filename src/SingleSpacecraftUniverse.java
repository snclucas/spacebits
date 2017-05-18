import java.math.BigDecimal;

import org.spacebits.Configuration;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;


public class SingleSpacecraftUniverse {

	public SingleSpacecraftUniverse() { 
		
		Universe universe = Configuration.getUniverse();
		Spacecraft spacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		universe.addSpacecraft(spacecraft);
		universe.updateSpacecraftLocation(spacecraft.getIdent(), 
				new Coordinates(new BigDecimal(8*Unit.kPc.value()), new BigDecimal(0.0), new BigDecimal(0.0)));
		
		
		
		universe.moveSpacecraft();
		
		run();	
	}

	private void run() {




	}

	public static void main(String[] args) {
		
		new SingleSpacecraftUniverse() ;
	}

}
