package org.spacebits.software;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.components.propulsion.ThrustDriveInterface;
import org.spacebits.components.propulsion.thrust.ThrustingEngine;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.status.SystemStatusMessage;

public class PropulsionManagementSoftware extends AbstractSoftware implements Software, ThrustDriveInterface {

	public static TypeInfo typeID = new TypeInfo("EngineManagementSoftware");



	public PropulsionManagementSoftware(String name) {
		super(name);
	}


	@Override
	public TypeInfo getType() {
		return typeID;
	}


	public SystemStatusMessage callDrive(double powerLevel) {
		SystemStatusMessage message = null;
		List<SpacecraftBusComponent> engines = computer.getSystemComputer().findComponentByCategory(Engine.category());
		for(SpacecraftBusComponent engine : engines)
			if(engine instanceof ThrustingEngine) {
				message =    ((ThrustDriveInterface) engine).callDrive(powerLevel);
			}
		return message;
	}


	public SystemStatusMessage callDrive(double powerLevel, String engineIdent) {
		

		
		ThrustingEngine engine = findEngineByIdent(engineIdent);
		if(engine == null)
			return new SystemStatusMessage(this, "No engine found with ident:"+engineIdent, 
					computer.getUniversalTime(), Status.CRITICAL);

		BusRequirement busRequirement = engine.callDrive(powerLevel);
		SystemStatusMessage operationPermittedMessage = computer.getSystemComputer().requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			engine.execute();
			return new SystemStatusMessage(
					engine, "Engine [ident:"+engine.getIdent() + "], power level set to " + powerLevel, computer.getUniversalTime(), Status.SUCCESS);
		}
		else
			return operationPermittedMessage;
	}

	public SystemStatusMessage callStop(String engineIdent) {
		return callDrive(0, engineIdent);
	}

	
	public SystemStatusMessage callStop() {
		return callDrive(0);
	}
	
	
	public SystemStatusMessage callVector(EngineVector engineVector, String engineIdent) {
		ThrustingEngine engine = findEngineByIdent(engineIdent);
		if(engine == null)
			return new SystemStatusMessage(null, "No engine found with id: "+engineIdent, computer.getUniversalTime(), Status.CRITICAL);

		BusRequirement busRequirement = engine.callVector(engineVector);
		SystemStatusMessage operationPermittedMessage = computer.getSystemComputer().requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			if(engine.isVectored()) {
				engine.execute();
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.getIdent() + "], engine vector set to " + engineVector, computer.getUniversalTime(), Status.SUCCESS);
			}
			else {
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.getIdent() + "], cannot be vectored", 
						computer.getUniversalTime(), Status.NOT_PERMITTED);
			}
		}
		else
			return operationPermittedMessage;
	}



	@Override
	public String getDescription() {
		return "Engine management";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}



	private ThrustingEngine findEngineByIdent(String ident) {
		List<SpacecraftBusComponent> engines = computer.getSystemComputer().findComponentByCategory(Engine.category());
		//TODO LOOK at thisif(engines != null)
			for(SpacecraftBusComponent engine : engines) {
				if(engine.getIdent() == ident)
					return (ThrustingEngine) engine;
			}
		return null;
	}


	@Override
	public String describe() {
		return "Software to manage and control the propulsion systems.";
	}

}
