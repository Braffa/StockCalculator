package com.nutmeg.transactions.handlers.input;

import com.nutmeg.transactions.beans.Transaction;

public interface IInputHandler {

	public void setHandler(IInputHandler handler);

	public void process(String[] attributes, Transaction transaction);

}
