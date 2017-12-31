package com.nutmeg.transactions.handlers.txn;

import java.util.Map;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.beans.TransactionKey;

public class BalanceHandler extends AbstractTxnHandler {

	public void processHere(Map<TransactionKey, Holding> holdingMap, Transaction transaction) {
		TransactionKey key = new TransactionKey(transaction.getAccount(), transaction.getAsset());
		Holding holding = holdingMap.get(key);
		if ((!holding.getAsset().equals("CASH")) && holding.getHolding() == 0.00) {
			process(holdingMap, transaction);
		} else {
			if (handler != null) {
				handler.processHere(holdingMap, transaction);
			}
		}
	}

	public void process(Map<TransactionKey, Holding> holdingMap, Transaction transaction) {
		TransactionKey key = new TransactionKey(transaction.getAccount(), transaction.getAsset());
		holdingMap.remove(key);
	}
}
