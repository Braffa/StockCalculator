package com.nutmeg.transactions;

public enum TxnTypeEnum {
	BOT, SLD, DIV, DEP, WDR;
	
	public static boolean isValid (String txn) {
		TxnTypeEnum [] trns = TxnTypeEnum.values();
		for (TxnTypeEnum t : trns) {
			if (t.name().equals(txn)) {
				return true;
			}
		}
		return false;
	}
}
