package org.braincycles.spacebits.components.propulsion.warp;

import org.braincycles.spacebits.components.propulsion.Engine;
import org.braincycles.spacebits.spacecraft.BusRequirement;

public interface WarpEngine extends Engine {
	BusRequirement callWarp(double powerLevel);
	BusRequirement callStop();
}
