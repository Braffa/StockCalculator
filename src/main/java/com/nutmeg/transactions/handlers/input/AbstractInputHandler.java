package com.nutmeg.transactions.handlers.input;

public abstract class AbstractInputHandler implements IInputHandler {

	protected IInputHandler handler;

	public void setHandler(IInputHandler handler) {
		this.handler = handler;
	}

}
