package org.spacebits.components;

public interface Identifiable {
	TypeInfo getType();
	TypeInfo getCategory();
	String getName();
	int getId();
	String describe();
}
