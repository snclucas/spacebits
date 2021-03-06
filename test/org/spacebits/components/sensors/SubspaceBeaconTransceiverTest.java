package org.spacebits.components.sensors;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorProfile;
import org.spacebits.components.sensors.SubspaceBeaconTransceiver;
import org.spacebits.navigation.BeaconSignal;
import org.spacebits.physics.Physics;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.OperationalSpecification;
import org.spacebits.spacecraft.PhysicalSpecification;
import org.spacebits.universe.Coordinates;
import org.spacebits.universe.Location;
import org.spacebits.universe.SimpleLocation;
import org.spacebits.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacebits.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacebits.universe.structures.SubspaceBeacon;
import org.spacebits.utils.Utils;

public class SubspaceBeaconTransceiverTest {
	
	
	
	@Test
	public void testDataRecord() {

		Location actualLocation = new SimpleLocation("Actual location", 
				new Coordinates(new BigDecimal(5.0 * Unit.Pc.value()),  new BigDecimal(5.0001000010000000344 * Unit.Pc.value()),  new BigDecimal(5.0 * Unit.Pc.value())));
		
		PhysicalSpecification physicalSpecification1 = new PhysicalSpecification(23, 12, 67, 45, 23);
		OperationalSpecification operationalSpecification1 = 
				new OperationalSpecification(23, 34, 45, 67);
		BusComponentSpecification busComponentSpecification = 
				new BusComponentSpecification(physicalSpecification1, operationalSpecification1);
		
		SubspaceBeaconTransceiver subspaceBeaconTransceiver = 
				new SubspaceBeaconTransceiver("Test", busComponentSpecification, 
						new SensorProfile(Sensor.SUBSPACE_RESONANCE, 1, 1));
		
		// Beacon coordinates
		BigDecimal b1x =  new BigDecimal(10 * Unit.Pc.value()); BigDecimal b1y =  new BigDecimal(50 * Unit.Pc.value()); BigDecimal b1z =  new BigDecimal(0 * Unit.Pc.value()); 
		BigDecimal b2x = new BigDecimal(-10 * Unit.Pc.value()); BigDecimal b2y =  new BigDecimal(0 * Unit.Pc.value()); BigDecimal b2z =  new BigDecimal(0 * Unit.Pc.value()); 
		BigDecimal b3x = new BigDecimal(10 * Unit.Pc.value()); BigDecimal b3y = new BigDecimal(10 * Unit.Pc.value()); BigDecimal b3z = new BigDecimal(-10 * Unit.Pc.value()); 
		BigDecimal b4x = new BigDecimal(10 * Unit.Pc.value()); BigDecimal b4y = new BigDecimal(-10 * Unit.Pc.value()); BigDecimal b4z = new BigDecimal(10 * Unit.Pc.value()); 
		
		SensorSignalResponseProfile sensorSignalResponseProfile = SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON);
		SubspaceBeacon subspaceBeacon1 = new SubspaceBeacon("Test beacon 1", new Coordinates(b1x, b1y, b1z), sensorSignalResponseProfile);
		SubspaceBeacon subspaceBeacon2 = new SubspaceBeacon("Test beacon 2", new Coordinates(b2x, b2y, b2z), sensorSignalResponseProfile);
		SubspaceBeacon subspaceBeacon3 = new SubspaceBeacon("Test beacon 3", new Coordinates(b3x, b3y, b3z), sensorSignalResponseProfile);
		SubspaceBeacon subspaceBeacon4 = new SubspaceBeacon("Test beacon 4", new Coordinates(b4x, b4y, b4z), sensorSignalResponseProfile);
		
		assertEquals("X coordinate of beacon 1 incorrect", b1x.doubleValue(), subspaceBeacon1.getCoordinates().get(0).doubleValue(), 0.001);
		assertEquals("Y coordinate of beacon 1 incorrect", b1y.doubleValue(), subspaceBeacon1.getCoordinates().get(1).doubleValue(), 0.001);
		assertEquals("Z coordinate of beacon 1 incorrect", b1z.doubleValue(), subspaceBeacon1.getCoordinates().get(2).doubleValue(), 0.001);
		
		assertEquals("X coordinate of beacon 2 incorrect", b2x.doubleValue(), subspaceBeacon2.getCoordinates().get(0).doubleValue(), 0.001);
		assertEquals("Y coordinate of beacon 2 incorrect", b2y.doubleValue(), subspaceBeacon2.getCoordinates().get(1).doubleValue(), 0.001);
		assertEquals("Z coordinate of beacon 2 incorrect", b2z.doubleValue(), subspaceBeacon2.getCoordinates().get(2).doubleValue(), 0.001);
		
		assertEquals("X coordinate of beacon 3 incorrect", b3x.doubleValue(), subspaceBeacon3.getCoordinates().get(0).doubleValue(), 0.001);
		assertEquals("Y coordinate of beacon 3 incorrect", b3y.doubleValue(), subspaceBeacon3.getCoordinates().get(1).doubleValue(), 0.001);
		assertEquals("Z coordinate of beacon 3 incorrect", b3z.doubleValue(), subspaceBeacon3.getCoordinates().get(2).doubleValue(), 0.001);
		
		assertEquals("X coordinate of beacon 4 incorrect", b4x.doubleValue(), subspaceBeacon4.getCoordinates().get(0).doubleValue(), 0.001);
		assertEquals("Y coordinate of beacon 4 incorrect", b4y.doubleValue(), subspaceBeacon4.getCoordinates().get(1).doubleValue(), 0.001);
		
		
		BigDecimal distanceToBeacon1 =  Utils.distanceToLocation(actualLocation.getCoordinates(), subspaceBeacon1.getCoordinates(), Unit.One);
		BigDecimal distanceToBeacon2 =  Utils.distanceToLocation(actualLocation.getCoordinates(), subspaceBeacon2.getCoordinates(), Unit.One);
		BigDecimal distanceToBeacon3 =  Utils.distanceToLocation(actualLocation.getCoordinates(), subspaceBeacon3.getCoordinates(), Unit.One);
		BigDecimal distanceToBeacon4 =  Utils.distanceToLocation(actualLocation.getCoordinates(), subspaceBeacon4.getCoordinates(), Unit.One);
		
		
		BigDecimal beaconSignalDispersion1 = Physics.distanceToSubspaceSignalDispersion(distanceToBeacon1);
		BigDecimal beaconSignalDispersion2 = Physics.distanceToSubspaceSignalDispersion(distanceToBeacon2);
		BigDecimal beaconSignalDispersion3 = Physics.distanceToSubspaceSignalDispersion(distanceToBeacon3);
		BigDecimal beaconSignalDispersion4 = Physics.distanceToSubspaceSignalDispersion(distanceToBeacon4);
		
		
		BigDecimal calculatedDistanceFromSignalDispersion1 = Physics.subspaceSignalDispersionToDistance(beaconSignalDispersion1);
		BigDecimal calculatedDistanceFromSignalDispersion2 = Physics.subspaceSignalDispersionToDistance(beaconSignalDispersion2);
		BigDecimal calculatedDistanceFromSignalDispersion3 = Physics.subspaceSignalDispersionToDistance(beaconSignalDispersion3);
		BigDecimal calculatedDistanceFromSignalDispersion4 = Physics.subspaceSignalDispersionToDistance(beaconSignalDispersion4);
				
		assertEquals("Distance to beacon 1 incorrect", calculatedDistanceFromSignalDispersion1.doubleValue()  / Unit.Pc.value(), distanceToBeacon1.doubleValue() / Unit.Pc.value(), 0.001);
		assertEquals("Distance to beacon 2 incorrect", calculatedDistanceFromSignalDispersion2.doubleValue()  / Unit.Pc.value(), distanceToBeacon2.doubleValue() / Unit.Pc.value(), 0.001);
		assertEquals("Distance to beacon 3 incorrect", calculatedDistanceFromSignalDispersion3.doubleValue()  / Unit.Pc.value(), distanceToBeacon3.doubleValue() / Unit.Pc.value(), 0.001);
		assertEquals("Distance to beacon 4 incorrect", calculatedDistanceFromSignalDispersion4.doubleValue()  / Unit.Pc.value(), distanceToBeacon4.doubleValue() / Unit.Pc.value(), 0.001);
		
		
		subspaceBeaconTransceiver.beaconSignals.add(new BeaconSignal(subspaceBeacon1, beaconSignalDispersion1.doubleValue()));
		subspaceBeaconTransceiver.beaconSignals.add(new BeaconSignal(subspaceBeacon2, beaconSignalDispersion2.doubleValue()));
		subspaceBeaconTransceiver.beaconSignals.add(new BeaconSignal(subspaceBeacon3, beaconSignalDispersion3.doubleValue()));
	//	subspaceBeaconTransceiver.beaconSignals.add(new BeaconSignal(subspaceBeacon4, beaconSignalDispersion4.doubleValue()));
		
		
		Coordinates position = subspaceBeaconTransceiver.calculatePosition();
		
		double expectedLocationX = actualLocation.getCoordinate(0).doubleValue();
		double expectedLocationY = actualLocation.getCoordinate(1).doubleValue();
		double expectedLocationZ = actualLocation.getCoordinate(2).doubleValue();
		
		assertEquals("X postion incorrect (1)", expectedLocationX/Unit.Pc.value(), position.get(0).doubleValue(), 0.0001);
		assertEquals("Y postion incorrect (1)", expectedLocationY/Unit.Pc.value(), position.get(1).doubleValue(), 0.0001);
		assertEquals("Z postion incorrect (1)", expectedLocationZ/Unit.Pc.value(), position.get(2).doubleValue(), 0.0001);
		
	}
	

}
