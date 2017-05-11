package org.spacebits.spacecraft;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacebits.components.computers.BasicSystemComputer;
import org.spacebits.components.computers.ComputerFactory;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.sensors.FractalSensorArray;
import org.spacebits.components.sensors.LinearSensorArray;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorFactory;
import org.spacebits.spacecraft.SimpleSpacecraft;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.status.SystemStatus;
import org.spacebits.structures.hulls.Hull;
import org.spacebits.structures.hulls.HullFactory;

public class SpacecraftBusTest {
	
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", hull);
	Bus spacecraftBus = new SpacecraftBus("spacecraftBus", spacecraft);

	@Test
	public void testSpacecraftBus() {
		assertEquals("Spacecraft bus name incorrect", "spacecraftBus", spacecraftBus.getName());
		assertEquals("Category for bus incorrect", Bus.category, spacecraftBus.getCategoryId());
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		assertEquals("There should be no bus components", 0, spacecraftBus.getComponents().size());
		assertNull(spacecraftBus.getSystemComputer());
		
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		SystemComputer computer = ComputerFactory.getComputer(BasicSystemComputer.type.toString());
		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.type.toString(), Sensor.OPTICAL, 1);
		spacecraftBus.addComponent(computer);
		spacecraftBus.addComponent(fractalSensor);

		assertEquals("There should be 2 bus components", 2, spacecraftBus.getComponents().size());
		
		System.out.println(   spacecraftBus.findComponent(Sensor.category).size()  );
		System.out.println(   spacecraftBus.findComponent(FractalSensorArray.type).size()  );
		System.out.println(   spacecraftBus.findComponent(FractalSensorArray.category).size()  );
		
		System.out.println(   spacecraftBus.findComponent(LinearSensorArray.type).size()  );
		
		System.out.println(   spacecraftBus.findComponent(SystemComputer.category).size() );
		System.out.println(   spacecraftBus.findComponent(BasicSystemComputer.type).size()  );

	}
	
	
	
	
	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}
