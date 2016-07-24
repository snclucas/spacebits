package org.spacebits.structures.storage.fuel;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacebits.Configuration;
import org.spacebits.consumables.Fuel;
import org.spacebits.data.SpacecraftDataProvider;
import org.spacebits.exceptions.ItemNotFoundException;
import org.spacebits.exceptions.NoFuelInTankException;
import org.spacebits.physics.Unit;
import org.spacebits.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.spacebits.structures.storage.fuel.FuelStorageTank;
import org.spacebits.structures.storage.fuel.FuelStorageTankFactory;
import org.spacebits.structures.storage.fuel.LiquidStorageTank;

public class FuelStorageTankTest {

	SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
	double capacity = 1000 * Unit.l;



	@Test
	public void testLiquidTank() {
		
		
		
		FuelStorageTank testLiquidTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.typeID.toString(), capacity);
		assertEquals("LiquidStorageTank category ID incorrect", FuelStorageTank.categoryID, testLiquidTank.getCategoryId());
		assertEquals("LiquidStorageTank type ID incorrect", LiquidStorageTank.typeID, testLiquidTank.getTypeId());
		
		
		FuelStorageTank testCryoTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.typeID.toString(), capacity);
		
		assertEquals("CryogenicLiquidStorageTank category ID incorrect", FuelStorageTank.categoryID, testCryoTank.getCategoryId());
		assertEquals("CryogenicLiquidStorageTank type ID incorrect", CryogenicLiquidStorageTank.typeID, testCryoTank.getTypeId());

		assertEquals("Tank capacity should be 1000L", capacity, testCryoTank.getCapacity(), 0.001);
		assertEquals("Fuel tank should be empty on creation", 0.0, testCryoTank.getAmountOfFuelInTank(), 0.001);

		Fuel fuel = spacecraftDataProvider.getFuel(Fuel.LIQUID_XENON);

		//Add 500L of fuel
		testCryoTank.setFuel(fuel, 436.45 * Unit.l);
		assertEquals("Fuel tank should have 436.45L", 436.45 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 43.645 * Unit.percent, testCryoTank.getFuelLevel(), 0.001);

		//Fill 100L of fuel
		testCryoTank.fillFuel(100.00 * Unit.l);
		assertEquals("Fuel tank should have 536.45L", 536.45 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 53.645 * Unit.percent, testCryoTank.getFuelLevel(), 0.001);

		//Try to overfill the tank - 
		testCryoTank.setFuel(fuel, 1000 * Unit.l);	
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);
		
		testCryoTank.fillFuel(10000 * Unit.l);	
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.removeFuel(300 * Unit.l);
		assertEquals("Fuel tank should have 700L", 700 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.removeFuel(800 * Unit.l);
		assertEquals("Fuel tank should have 0L", 0 * Unit.l, testCryoTank.getAmountOfFuelInTank(), 0.001);
		
		
		assertEquals("Nominal and operating power for fuel tank not equal", testCryoTank.getNominalPower(), testCryoTank.getOperatingPower(), 0.0001);
		assertEquals("Nominal and operating CPU for fuel tank not equal", testCryoTank.getNominalCPUThroughput(), testCryoTank.getOperatingCPUThroughput(), 0.0001);
	}


	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=NoFuelInTankException.class)
	public void testTankThrowsErrorIfNoFuel() {
		// Try to get the fuel, should throw NoFuelInTankException
		FuelStorageTank testTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.typeID.toString(), capacity);
		testTank.getFuel();
	}

	
	@Test(expected=ItemNotFoundException.class)
	public void testNoSuchTank() {
		FuelStorageTank noSuchTank = FuelStorageTankFactory.getFuelStorageTank("ThisTankDoesNotExisit", capacity);
	}



}