package com.nutmeg.transactions.handlers.input;

import java.math.BigDecimal;

import com.nutmeg.transactions.beans.Transaction;

public class PriceHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		try {
			Double.parseDouble(attributes[4]);
			transaction.setValid(true);
			transaction.setPrice(new BigDecimal(attributes[4]));
		} catch (Exception e) {
			transaction.setValid(false);
			transaction.setPrice(new BigDecimal(0));
		}
		if (transaction.isValid() && handler != null) {
			handler.process(attributes, transaction);
		}
	}

}
