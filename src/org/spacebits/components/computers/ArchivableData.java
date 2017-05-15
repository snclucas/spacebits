package org.spacebits.components.computers;

import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;

public interface ArchivableData extends Identifiable {
	TypeInfo category = new TypeInfo("ArchivableData");
	String getData();
}
