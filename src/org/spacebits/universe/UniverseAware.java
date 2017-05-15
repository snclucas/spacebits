package org.spacebits.universe;

import org.spacebits.Configuration;

public class UniverseAware {
	Universe getUniverse() {
		return Configuration.getUniverse();
	}
}
