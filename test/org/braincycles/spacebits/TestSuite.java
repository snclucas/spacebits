package org.braincycles.spacebits;

import org.braincycles.spacebits.components.ComponentsTestSuite;
import org.braincycles.spacebits.components.computers.ComputerTestSuite;
import org.braincycles.spacebits.components.energygeneration.SubspacePowerGeneratorTest;
import org.braincycles.spacebits.components.engines.thrust.FuelSubSystemTest;
import org.braincycles.spacebits.components.propulsion.PropulsionTestSuite;
import org.braincycles.spacebits.components.sensors.SensorTestSuite;
import org.braincycles.spacebits.consumables.FuelTest;
import org.braincycles.spacebits.data.SpacecraftComponentDataTest;
import org.braincycles.spacebits.materials.CompositeMaterialTest;
import org.braincycles.spacebits.physics.PhysicsTest;
import org.braincycles.spacebits.physics.UnitsTest;
import org.braincycles.spacebits.software.PropulsionManagementSoftwareTest;
import org.braincycles.spacebits.spacecraft.SpacecraftTestSuite;
import org.braincycles.spacebits.status.SystemStatusTest;
import org.braincycles.spacebits.structures.hulls.HullTest;
import org.braincycles.spacebits.structures.storage.fuel.FuelStorageTankTest;
import org.braincycles.spacebits.universe.UniverseTestSuite;
import org.braincycles.spacebits.universe.celestialobjects.SensorSignalResponseProfileTest;
import org.braincycles.spacebits.universe.celestialobjects.SubspaceBeaconTest;
import org.braincycles.spacebits.utils.math.DistanceSolverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SubspaceBeaconTest.class,
	SensorTestSuite.class,
	SubspacePowerGeneratorTest.class,
	SpacecraftTestSuite.class,
	FuelSubSystemTest.class,
	FuelStorageTankTest.class,
	PropulsionTestSuite.class,
	ComputerTestSuite.class,
	UniverseTestSuite.class,
	UnitsTest.class,
	PhysicsTest.class,
	ComponentsTestSuite.class,
	HullTest.class,
	FuelTest.class,
	DistanceSolverTest.class,
	CompositeMaterialTest.class,
	SpacecraftComponentDataTest.class,
	PropulsionManagementSoftwareTest.class,
	SensorSignalResponseProfileTest.class,
	SystemStatusTest.class
})

public class TestSuite {


}

