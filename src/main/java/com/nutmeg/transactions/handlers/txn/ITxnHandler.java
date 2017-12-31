package com.nutmeg.transactions.handlers.txn;

import java.util.Map;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.beans.TransactionKey;

public interface ITxnHandler {

	public void setHandler(ITxnHandler handler);

	public void processHere(Map<TransactionKey, Holding> holdingMap, Transaction transaction);

	public void process(Map<TransactionKey, Holding> holdingMap, Transaction transaction);

}
