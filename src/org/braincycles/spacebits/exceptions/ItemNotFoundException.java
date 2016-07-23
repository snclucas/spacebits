package org.braincycles.spacebits.exceptions;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException(String meessage) {
		super(meessage);
	}

}
