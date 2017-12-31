package com.nutmeg.transactions.handlers.txn;

import java.math.BigDecimal;
import java.util.Map;

import com.nutmeg.transactions.TxnTypeEnum;
import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.beans.TransactionKey;

public class DivTxnHandler extends AbstractTxnHandler {

	public void processHere(Map<TransactionKey, Holding> holdingMap, Transaction transaction) {
		if (transaction.getTxnType().equals(TxnTypeEnum.DIV.name())) {
			process(holdingMap, transaction);
		} else {
			if (handler != null) {
				handler.processHere(holdingMap, transaction);
			}
		}
	}

	public void process(Map<TransactionKey, Holding> holdingMap, Transaction transaction) {
		TransactionKey cashKey = new TransactionKey(transaction.getAccount(), "CASH");
		Holding cashHolding = holdingMap.get(cashKey);
		BigDecimal div = transaction.getUnits();
		cashHolding.setHoldings(cashHolding.getHolding() + div.doubleValue());
	}

}
