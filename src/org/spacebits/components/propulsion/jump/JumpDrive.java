package org.spacebits.components.propulsion.jump;

import org.spacebits.components.propulsion.Engine;
import org.spacebits.spacecraft.BusRequirement;

public interface JumpDrive extends Engine {
	BusRequirement callJump(double powerLevel);
	BusRequirement callStop();
}
