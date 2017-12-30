package com.nutmeg.transactions.handlers.input;

import com.nutmeg.transactions.beans.Transaction;

public class AssetHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		if (attributes[5].length() != 4) {
			transaction.setValid(false);
			transaction.setAsset(null);
		} else {
			transaction.setValid(true);
			transaction.setAsset(attributes[5]);
			if (handler != null) {
				handler.process(attributes, transaction);
			}
		}
		
	}

}
