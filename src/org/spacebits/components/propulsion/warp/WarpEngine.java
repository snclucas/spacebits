package org.spacebits.components.propulsion.warp;

import org.spacebits.components.propulsion.Engine;
import org.spacebits.spacecraft.BusRequirement;

public interface WarpEngine extends Engine {
	BusRequirement callWarp(double powerLevel);
	BusRequirement callStop();
}
