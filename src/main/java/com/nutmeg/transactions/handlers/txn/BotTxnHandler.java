package com.nutmeg.transactions.handlers.txn;

import java.math.BigDecimal;
import java.util.Map;

import com.nutmeg.transactions.TxnTypeEnum;
import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;

public class BotTxnHandler extends AbstractTxnHandler {

	public void processHere(Map<String, Holding> holdingMap, Transaction transaction) {
		if (transaction.getTxnType().equals(TxnTypeEnum.BOT.name())) {
			process(holdingMap, transaction);
		} else {
			if (handler != null) {
				handler.processHere(holdingMap, transaction);
			}
		}
	}

	public void process(Map<String, Holding> holdingMap, Transaction transaction) {
		String key = transaction.getAccount() + transaction.getAsset();
		BigDecimal units = transaction.getUnits();
		if (holdingMap.containsKey(key)) {
			Holding holding = holdingMap.get(key);
			holding.setHoldings(holding.getHolding() + units.doubleValue());
		} else {
			Holding holding = new Holding(transaction.getAsset(), units.doubleValue());
			holdingMap.put(key, holding);
		}
		BigDecimal bot = transaction.getPrice().multiply(transaction.getUnits());
		Holding cashHolding = holdingMap.get(transaction.getAccount() + "CASH");
		cashHolding.setHoldings(subtractHoldingAmount(bot, cashHolding.getHolding()));

	}

}
