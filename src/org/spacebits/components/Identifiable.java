package org.spacebits.components;

public interface Identifiable {
	TypeInfo getTypeId();
	TypeInfo getCategoryId();
	String getName();
	int getId();
	String describe();
}
