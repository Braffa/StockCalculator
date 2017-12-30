package com.nutmeg.transactions.handlers.txn;

import java.math.BigDecimal;
import java.util.Map;

import com.nutmeg.transactions.TxnTypeEnum;
import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;

public class DivTxnHandler extends AbstractTxnHandler {

	public void processHere(Map<String, Holding> holdingMap, Transaction transaction) {
		if (transaction.getTxnType().equals(TxnTypeEnum.DIV.name())) {
			process (holdingMap, transaction);
		} else {
			if (handler != null) {
				handler.processHere(holdingMap, transaction);
			}
		}
	}
	
	public void process(Map<String, Holding> holdingMap, Transaction transaction) {
		Holding cashHolding = holdingMap.get(transaction.getAccount() + "CASH");
		BigDecimal div = transaction.getUnits();
		cashHolding.setHoldings(cashHolding.getHolding() + div.doubleValue());
	}

}
