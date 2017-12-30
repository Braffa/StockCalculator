package com.nutmeg.transactions.handlers.txn;

import java.math.BigDecimal;
import java.util.Map;

import com.nutmeg.transactions.TxnTypeEnum;
import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;

public class SldTxnHandler extends AbstractTxnHandler {

	public void processHere(Map<String, Holding> holdingMap, Transaction transaction) {
		if (transaction.getTxnType().equals(TxnTypeEnum.SLD.name())) {
			process (holdingMap, transaction);
		} else {
			if (handler != null) {
				handler.processHere(holdingMap, transaction);
			}
		}
	}
	
	public void process(Map<String, Holding> holdingMap, Transaction transaction) {
		String key = transaction.getAccount() + transaction.getAsset();
		BigDecimal unitsSold = transaction.getUnits();
		if (holdingMap.containsKey(key)) {
			Holding holding = holdingMap.get(key);
			holding.setHoldings(holding.getHolding() - unitsSold.doubleValue());
		} else {
			double sold = 0 - unitsSold.doubleValue();
			Holding holding = new Holding(transaction.getAsset(), sold);
			holdingMap.put(key, holding);
		}
		BigDecimal sld = transaction.getPrice().multiply(transaction.getUnits());
		Holding cashHolding = holdingMap.get(transaction.getAccount() + "CASH");
		cashHolding.setHoldings(cashHolding.getHolding() + sld.doubleValue());
	}
}
