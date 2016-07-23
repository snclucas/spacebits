package org.braincycles.spacebits.structures.hulls;

import static org.junit.Assert.assertEquals;

import org.braincycles.spacebits.Configuration;
import org.braincycles.spacebits.data.LocalMaterialDataProvider;
import org.braincycles.spacebits.materials.Material;
import org.braincycles.spacebits.physics.Unit;
import org.braincycles.spacebits.spacecraft.BusComponentSpecification;
import org.braincycles.spacebits.spacecraft.OperationalSpecification;
import org.braincycles.spacebits.spacecraft.PhysicalSpecification;
import org.braincycles.spacebits.structures.hulls.Hull;
import org.braincycles.spacebits.structures.hulls.SimpleMonocoqueHull;
import org.junit.Test;

public class HullTest {

	Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.ALUMINUM);
	double nominalPower = 1 * Unit.kW;
	double nominalCPUThroughput = 1 * Unit.kFLOP;
	double length = 10 * Unit.m;
	double width = 10 * Unit.m;
	double height = width;
	double volume  = length*width * Unit.m3;
	double thickness = 0.25 * Unit.m;
	double mass = material.getDensity() * volume; // Not needed, calculated by Hull

	double impactResistanceModifierFromHullConstruction = 1.1;
	double emResistanceModifierFromHullConstruction = 2.2;
	double thermalResistanceModifierFromHullConstruction = 3.3;
	double radiationResistanceModifierFromHullConstruction = 4.4;
	
	BusComponentSpecification busResourceSpecification = new BusComponentSpecification(
			new PhysicalSpecification(mass, volume, length, width, height),
			new OperationalSpecification( nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));
	
	HullSpecification hullSpecification = new HullSpecification("Simple test hull spec", busResourceSpecification, thickness, material, 
			impactResistanceModifierFromHullConstruction, emResistanceModifierFromHullConstruction,
			thermalResistanceModifierFromHullConstruction, radiationResistanceModifierFromHullConstruction);
	
	HullSpecification hullSpecificationNoModifiers = new HullSpecification("Simple test hull spec", busResourceSpecification, thickness, material);

	@Test
	public void testSimpleHull() {
		Hull hull = new SimpleMonocoqueHull("TestHull", hullSpecification, Hull.SPHEROID);
		
		assertEquals("Hull spec name not set correctly", "Simple test hull spec", hullSpecification.getName());
		assertEquals("Hull CPU not set correctly", nominalCPUThroughput, hull.getNominalCPUThroughput(), 0.001);
		assertEquals("Hull power not set correctly", nominalPower, hull.getNominalPower(), 0.001);
		assertEquals("Hull thickness not set correctly", thickness, hull.getThickness(), 0.001);
		assertEquals("Hull width not set correctly", width, hull.getWidth(), 0.001);
		assertEquals("Hull length not set correctly", length, hull.getLength(), 0.001);
	}

	
	@Test
	public void testHullResistanceModifiers() {
		/* Hull resistance modifiers, multiply the underlying material resistances. */
		
		// Do not supply the resistance modifiers - should default to 1.0
		
		Hull hull = new SimpleMonocoqueHull("TestHull", hullSpecificationNoModifiers, Hull.SPHEROID);
		
		assertEquals("Hull impact resistance modifier not set correctly", 
				material.getImpactResistance() * 1.0, hull.getImpactResistance(), 0.001);
		assertEquals("Hull EM resistance modifier not set correctly", 
				material.getEMResistance() * 1.0, hull.getEMResistance(), 0.001);
		assertEquals("Hull thermal resistance modifier not set correctly", 
				material.getThermalResistance() * 1.0, hull.getThermalResistance(), 0.001);
		assertEquals("Hull radiation resistance modifier not set correctly", 
				material.getRadiationResistance() * 1.0, hull.getRadiationResistance(), 0.001);

		
		// Now with modifiers
		
		hull = new SimpleMonocoqueHull("TestHull", hullSpecification, Hull.SPHEROID);
		
		assertEquals("Hull impact resistance modifier not set correctly", 
				material.getImpactResistance() * impactResistanceModifierFromHullConstruction, hull.getImpactResistance(), 0.001);
		assertEquals("Hull EM resistance modifier not set correctly", 
				material.getEMResistance() * emResistanceModifierFromHullConstruction, hull.getEMResistance(), 0.001);
		assertEquals("Hull thermal resistance modifier not set correctly", 
				material.getThermalResistance() * thermalResistanceModifierFromHullConstruction, hull.getThermalResistance(), 0.001);
		assertEquals("Hull radiation resistance modifier not set correctly", 
				material.getRadiationResistance() * radiationResistanceModifierFromHullConstruction, hull.getRadiationResistance(), 0.001);

	}
	

	@Test
	public void testHullVolumeCalculation() {
		Hull spheroidHull = new SimpleMonocoqueHull("TestHull", hullSpecification, Hull.SPHEROID);
		
		double volumeOfSphericalHull = (4.0*Math.PI/3.0)*width*height*length;
		double volumeOfSphericalHullPlusHullThickness = (4.0*Math.PI/3.0)*(width+2*thickness)*(height+2*thickness)*(length+2*thickness);
		double volumeDifference = volumeOfSphericalHullPlusHullThickness - volumeOfSphericalHull;

		double massOfHullSHell = volumeDifference * material.getDensity() * Hull.HULL_VOLUME_FRACTION;

		assertEquals("Hull shell mass not calculated correctly", massOfHullSHell, spheroidHull.getMass(), 0.001);
		
		
		Hull rectangularHull = new SimpleMonocoqueHull("TestHull", hullSpecification, Hull.RECTANGULAR);
		
		double volumeOfRectangularHull = width*length*height;
		double volumeOfRectangularHullPlusHullThickness = (width+2*thickness)*(length+2*thickness)*(height+2*thickness);
		volumeDifference = volumeOfRectangularHullPlusHullThickness - volumeOfRectangularHull;

		massOfHullSHell = volumeDifference * material.getDensity() * Hull.HULL_VOLUME_FRACTION;

		assertEquals("Hull shell mass not calculated correctly", massOfHullSHell, rectangularHull.getMass(), 0.001);

	}

	

}
