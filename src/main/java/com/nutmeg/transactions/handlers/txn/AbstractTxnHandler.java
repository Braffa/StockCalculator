package com.nutmeg.transactions.handlers.txn;

import java.math.BigDecimal;

public abstract class AbstractTxnHandler implements ITxnHandler {

	protected ITxnHandler handler;

	public void setHandler(ITxnHandler handler) {
		this.handler = handler;
	}

	protected double subtractHoldingAmount(BigDecimal holdingAmount, double cashAmount) {
		String cashAsString = Double.toString(cashAmount);
		BigDecimal cashBigDecimal = new BigDecimal(cashAsString);
		BigDecimal cash = cashBigDecimal.subtract(holdingAmount);
		BigDecimal rounded = cash.setScale(4, BigDecimal.ROUND_DOWN);
		return rounded.doubleValue();
	}

}
