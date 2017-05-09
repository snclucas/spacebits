package org.spacebits.components;

import org.spacebits.status.SystemStatus;

public interface Onlineable {
	boolean isOnline();
	SystemStatus online();
}
