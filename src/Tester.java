

import org.spacebits.Configuration;
import org.spacebits.data.LocalMaterialDataProvider;
import org.spacebits.materials.Material;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullSpecification;
import org.spacebits.structures.hulls.SimpleMonocoqueHull;


public class Tester {
	
	
	public Tester() {
		Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.REINFORCED_TITANIUM);
		BusComponentSpecification busSpecs = new BusComponentSpecification(
				new PhysicalSpecification(1, 1, 100 * Unit.m.value(), 35 * Unit.m.value(), 35 * Unit.m.value()),
				new OperationalSpecification(0, 0, 0, 0));
		HullSpecification hullSpecification = new HullSpecification("SimpleMonocoqueHullSpec",
				busSpecs, 0.25 * Unit.m.value(), material);
		
		Hull hull = new SimpleMonocoqueHull("Simple hull", hullSpecification, Hull.SPHEROID);
		
		Spacecraft spacecraft = new SimpleSpacecraft("", hull);
		

		
		
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Tester();
	}
	
	

}
