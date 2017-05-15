package org.spacebits.spacecraft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.sensors.FractalSensorArray;
import org.spacebits.components.sensors.LinearSensorArray;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorFactory;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class SpacecraftBusTest {
	
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", hull);
	Bus spacecraftBus = new SpacecraftBus("spacecraftBus", spacecraft);
	
	
	@Before
	public void setUp() {
		
	}
	

	@Test
	public void testSpacecraftBus() {
		assertEquals("Spacecraft bus name incorrect", "spacecraftBus", spacecraftBus.getName());
		assertEquals("Category for bus incorrect", Bus.category, spacecraftBus.getCategoryId());
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		assertEquals("There should be no bus components", 0, spacecraftBus.getComponents().size());
		assertNull(spacecraftBus.getSystemComputer());
		
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		SystemComputer computer = ComputerFactory.getComputer(BasicSystemComputer.type.toString());
		spacecraftBus.addComponent(computer);
		assertNotNull(spacecraftBus.getSystemComputer());
		
		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.type.toString(), Sensor.OPTICAL, 1);
		spacecraftBus.addComponent((SpacecraftBusComponent)fractalSensor);

		assertEquals("There should be 2 bus components", 2, spacecraftBus.getComponents().size());
		
		assertEquals("There should be 1 Sensor.category component", 1, spacecraftBus.findComponent(Sensor.category).size());
		assertEquals("There should be 1 FractalSensorArray.type component", 1, spacecraftBus.findComponent(FractalSensorArray.type).size());
		assertEquals("There should be 1 FractalSensorArray.category component", 1, spacecraftBus.findComponent(FractalSensorArray.category).size());
		assertEquals("There should be 1 LinearSensorArray.type component", 0, spacecraftBus.findComponent(LinearSensorArray.type).size());
		assertEquals("There should be 1 SystemComputer.category component", 1, spacecraftBus.findComponent(SystemComputer.category).size());
		assertEquals("There should be 1 BasicSystemComputer.type component", 1, spacecraftBus.findComponent(BasicSystemComputer.type).size());
	}
	
	
	
	
	
	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}
