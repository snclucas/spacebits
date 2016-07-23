package org.braincycles.spacebits;

import org.braincycles.spacebits.status.SystemStatus;

public interface Diagnosable {
	SystemStatus runDiagnostics(int level);
}
