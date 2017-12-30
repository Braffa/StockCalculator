package com.nutmeg.transactions.handlers.txn;

import java.util.Map;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;

public interface ITxnHandler {

	public void setHandler(ITxnHandler handler);

	public void processHere (Map<String, Holding> holdingMap, Transaction transaction);
	
	public void process(Map<String, Holding> holdingMap, Transaction transaction);

}
