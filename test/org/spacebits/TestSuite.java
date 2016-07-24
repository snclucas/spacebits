package org.spacebits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.spacebits.components.ComponentsTestSuite;
import org.spacebits.components.computers.ComputerTestSuite;
import org.spacebits.components.energygeneration.SubspacePowerGeneratorTest;
import org.spacebits.components.engines.thrust.FuelSubSystemTest;
import org.spacebits.components.propulsion.PropulsionTestSuite;
import org.spacebits.components.sensors.SensorTestSuite;
import org.spacebits.consumables.FuelTest;
import org.spacebits.data.SpacecraftComponentDataTest;
import org.spacebits.materials.CompositeMaterialTest;
import org.spacebits.physics.PhysicsTest;
import org.spacebits.physics.UnitsTest;
import org.spacebits.software.PropulsionManagementSoftwareTest;
import org.spacebits.spacecraft.SpacecraftTestSuite;
import org.spacebits.status.SystemStatusTest;
import org.spacebits.structures.hulls.HullTest;
import org.spacebits.structures.storage.fuel.FuelStorageTankTest;
import org.spacebits.universe.UniverseTestSuite;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfileTest;
import org.spacebits.universe.celestialobjects.SubspaceBeaconTest;
import org.spacebits.utils.math.DistanceSolverTest;

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

