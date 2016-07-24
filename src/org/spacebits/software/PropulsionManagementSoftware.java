package org.spacebits.software;

import java.util.List;

import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.EngineVector;
import org.spacebits.components.propulsion.ThrustDriveInterface;
import org.spacebits.components.propulsion.thrust.ThrustEngine;
import org.spacebits.spacecraft.BusRequirement;
import org.spacebits.status.SystemStatusMessage;

public class PropulsionManagementSoftware extends AbstractSoftware implements Software, ThrustDriveInterface {

	public static TypeInfo typeID = new TypeInfo("EngineManagementSoftware");



	public PropulsionManagementSoftware(String name, SystemComputer computer) {
		super(name, computer);
	}


	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	public SystemStatusMessage callDrive(double powerLevel) {
		SystemStatusMessage message = null;
		List<SpacecraftBusComponent> engines = computer.findBusComponent(Engine.categoryID);
		for(SpacecraftBusComponent engine : engines)
			if(engine instanceof ThrustEngine) {
				message =    ((ThrustDriveInterface) engine).callDrive(powerLevel);
			}
		return message;
	}


	public SystemStatusMessage callDrive(double powerLevel, int engineId) {
		ThrustEngine engine = findEngineByIdent(engineId);
		if(engine == null)
			return new SystemStatusMessage(this, "No engine found with ident:"+engineId, 
					computer.getUniversalTime(), Status.CRITICAL);

		BusRequirement busRequirement = engine.callDrive(powerLevel);
		SystemStatusMessage operationPermittedMessage = computer.requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			engine.execute();
			return new SystemStatusMessage(
					engine, "Engine [ident:"+engine.getId() + "], power level set to " + powerLevel, computer.getUniversalTime(), Status.SUCCESS);
		}
		else
			return operationPermittedMessage;
	}

	public SystemStatusMessage callStop(int engineId) {
		return callDrive(0, engineId);
	}

	
	public SystemStatusMessage callStop() {
		return callDrive(0);
	}
	
	
	public SystemStatusMessage callVector(EngineVector engineVector, int engineId) {
		ThrustEngine engine = findEngineByIdent(engineId);
		if(engine == null)
			return new SystemStatusMessage(null, "No engine found with id: "+engineId, computer.getUniversalTime(), Status.CRITICAL);

		BusRequirement busRequirement = engine.callVector(engineVector);
		SystemStatusMessage operationPermittedMessage = computer.requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			if(engine.isVectored()) {
				engine.execute();
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.getId() + "], engine vector set to " + engineVector, computer.getUniversalTime(), Status.SUCCESS);
			}
			else {
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.getId() + "], cannot be vectored", 
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



	private ThrustEngine findEngineByIdent(int id) {
		List<SpacecraftBusComponent> engines = computer.findBusComponent(Engine.categoryID);
		for(SpacecraftBusComponent engine : engines) {
			if(engine.getId() == id)
				return (ThrustEngine) engine;
		}
		return null;
	}

}
