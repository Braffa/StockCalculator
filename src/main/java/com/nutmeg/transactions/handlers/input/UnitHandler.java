package com.nutmeg.transactions.handlers.input;

import java.math.BigDecimal;

import com.nutmeg.transactions.beans.Transaction;

public class UnitHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		try {
			Double.parseDouble(attributes[3]);
			transaction.setValid(true);
			transaction.setUnits(new BigDecimal(attributes[3]));
		} catch (Exception e) {
			transaction.setValid(false);
			transaction.setUnits(new BigDecimal(0));
		}
		if (transaction.isValid() && handler != null) {
			handler.process(attributes, transaction);
		}
	}

}
