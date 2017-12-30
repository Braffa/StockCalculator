package com.nutmeg.transactions.handlers.input;

import com.nutmeg.transactions.beans.Transaction;

public class AccountHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		if (attributes[0].length() != 8) {
			transaction.setValid(false);
		} else {
			transaction.setValid(true);
			transaction.setAccount(attributes[0]);
			if (handler != null) {
				handler.process(attributes, transaction);
			}
		}
	}
}
