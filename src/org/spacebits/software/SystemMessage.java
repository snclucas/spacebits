package org.spacebits.software;

import org.spacebits.components.Identifiable;

public class SystemMessage implements Message {
	
	private Identifiable reciever;
	private Identifiable sender;
	private String messageBody;
	private double universalDate;
	
	
	public SystemMessage(Identifiable reciever, Identifiable sender,
			String messageBody, double universalDate) {
		super();
		this.reciever = reciever;
		this.sender = sender;
		this.messageBody = messageBody;
		this.universalDate = universalDate;
	}

	@Override
	public int getRecieverId() {
		return reciever.getId();
	}

	@Override
	public int getSenderId() {
		return sender.getId();
	}

	@Override
	public String getMessage() {
		return messageBody;
	}

	@Override
	public double getUniversalDate() {
		return universalDate;
	}

}
