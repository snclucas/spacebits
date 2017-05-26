import java.math.BigDecimal;

import org.spacebits.Configuration;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.Star;


public class SingleSpacecraftUniverse {

	public SingleSpacecraftUniverse() { 
		
		Universe universe = Universe.getInstance();
		Spacecraft spacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		Star sol = new Star("Sol", Star.G_CLASS_STAR, new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		universe.addSpacecraft(spacecraft, sol.getCoordinates());
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
