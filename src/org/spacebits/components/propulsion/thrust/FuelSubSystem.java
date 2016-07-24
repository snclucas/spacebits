package org.spacebits.components.propulsion.thrust;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spacebits.components.AbstractBusComponent;
import org.spacebits.components.BusCommunicator;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.software.Message;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;
import org.spacebits.status.SystemStatusMessage;
import org.spacebits.structures.storage.fuel.FuelStorageTank;

public class FuelSubSystem extends AbstractBusComponent implements SpacecraftBusComponent, BusCommunicator {
	
	public static TypeInfo categoryID = new TypeInfo("FuelSubSystem");
	
	public static int PROPULSION_FUEL_SUBSYSTEM = 1;
	public static int REACTOR_FUEL_SUBSYSTEM = 2;
	
	
	
	public final static String BASIC_FUEL_SUBSYSTEM = "Basic fuel subsystem";

	protected List<FuelStorageTank> fuelTanks;
	
	protected int fuelSubsystemType;
	
	
	@Override
	public final TypeInfo getCategoryId() {
		return categoryID;
	}
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	
	
	public FuelSubSystem(String name, BusComponentSpecification busResourceSpecification, int fuelSubsystemType) {
		super(name, busResourceSpecification);	
		this.fuelTanks = new ArrayList<FuelStorageTank>();
		this.fuelSubsystemType = fuelSubsystemType;
	}
	
	
	public FuelSubSystem(String name, BusComponentSpecification busResourceSpecification, 
			List<FuelStorageTank> fuelTanks, int fuelSubsystemType) {
		super(name, busResourceSpecification);
		
		this.fuelTanks = fuelTanks;
		this.fuelSubsystemType = fuelSubsystemType;
	}
	
	
	public int getFuelSubsystemType() {
		return fuelSubsystemType;
	}
	
	
	public SystemStatusMessage registerSystemComputer(SystemComputer systemComputer) {
		SystemStatusMessage systemStatusMessage = super.registerSystemComputer(systemComputer);
		for(FuelStorageTank tank : fuelTanks)
			tank.registerSystemComputer(systemComputer);
		return systemStatusMessage;
	}
	


	@Override
	public double getMass() {
		double totalMassOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalMassOfFuelTanks += tank.getMass();	
		return super.getMass() + totalMassOfFuelTanks;
	}
	
	
	@Override
	public double getVolume() {
		double totalVolumeOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalVolumeOfFuelTanks += tank.getVolume();	
		return super.getVolume() + totalVolumeOfFuelTanks;
	}


	public List<FuelStorageTank> getFuelTanks() {
		return Collections.unmodifiableList(fuelTanks);
	}
	
	
	public boolean hasFuelTanks() {
		return fuelTanks.size() > 0;
	}
	
	
	public void addFuelTank(FuelStorageTank fuelTank) {
		fuelTanks.add(fuelTank);
	}


	@Override
	public double getOperatingPower() {
		return busResourceSpecification.getNominalPower();
	}


	@Override
	public double getOperatingCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout();
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void recieveMessage(Message message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String describe() {
		return this.getName();
	}


	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", systemComputer.getUniversalTime(), Status.OK);
		
		for(FuelStorageTank tank : fuelTanks)
			tank.online();
		
		return systemStatus; 
	}


	

}
