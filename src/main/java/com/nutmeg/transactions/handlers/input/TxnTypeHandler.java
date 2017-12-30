package com.nutmeg.transactions.handlers.input;

import com.nutmeg.transactions.TxnTypeEnum;
import com.nutmeg.transactions.beans.Transaction;

public class TxnTypeHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		if (attributes[2].length() != 3 || !TxnTypeEnum.isValid(attributes[2])) {
			transaction.setValid(false);
			transaction.setTxnType(null);
		} else {
			transaction.setValid(true);
			transaction.setTxnType(attributes[2]);
			if (handler != null) {
				handler.process(attributes, transaction);
			}
		}
	}
}
