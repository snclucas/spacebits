package org.braincycles.spacebits.software;

public interface Message {

	int getRecieverId();
	
	int getSenderId();
	
	String getMessage();
	
	double getUniversalDate();	
	
}
