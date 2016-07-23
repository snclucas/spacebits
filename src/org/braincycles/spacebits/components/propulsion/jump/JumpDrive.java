package org.braincycles.spacebits.components.propulsion.jump;

import org.braincycles.spacebits.components.propulsion.Engine;
import org.braincycles.spacebits.spacecraft.BusRequirement;

public interface JumpDrive extends Engine {
	BusRequirement callJump(double powerLevel);
	BusRequirement callStop();
}
