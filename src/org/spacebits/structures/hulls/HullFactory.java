package org.spacebits.structures.hulls;

import org.spacebits.Configuration;
import org.spacebits.data.LocalMaterialDataProvider;
import org.spacebits.materials.Material;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;

public class HullFactory {


	public static Hull getHull(String hullType){
		if(hullType.equalsIgnoreCase("Shuttle")){			
			double nominalPower = 1 * Unit.kW;
			double nominalCPUThroughput = 1 * Unit.kFLOP;

			double length = 10 * Unit.m;
			double width = 6 * Unit.m;
			double height = 6 * Unit.m;
			double volume  = length*width * Unit.m3;
			double thickness = 0.25 * Unit.m;
			Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.REINFORCED_TITANIUM);
			double mass = material.getDensity() * volume; // Not needed, calculated by Hull
			BusComponentSpecification busSpecs = new BusComponentSpecification(
					new PhysicalSpecification(mass, volume, length, width, height),
					new OperationalSpecification(nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));

			HullSpecification hullSpecification = new HullSpecification("SimpleMonocoqueHullSpec",
					busSpecs, thickness, material);

			return new SimpleMonocoqueHull("Shuttle", hullSpecification, Hull.SPHEROID);
		}

		return null;
	}


}
