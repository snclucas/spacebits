package org.spacebits.components;

import org.spacebits.status.SystemStatus;

public interface Diagnosable {
	SystemStatus runDiagnostics(int level);
}
