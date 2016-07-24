package org.spacebits.components.propulsion;

import org.spacebits.status.SystemStatusMessage;

public interface ThrustDriveInterface {
	//Basic engines interaction
	SystemStatusMessage callDrive(double powerLevel);
	SystemStatusMessage callDrive(double powerLevel, int engineIdent);
	SystemStatusMessage callStop(int engineIdent);
	SystemStatusMessage callStop();
	SystemStatusMessage callVector(EngineVector engineVector, int engineIdent);
}
