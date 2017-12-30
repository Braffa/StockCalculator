package com.nutmeg.transactions.beans;
/*
Account	 - A string of 8 alphanumberic characters
Date		 - In YYYYMMDD format
TxnType	 - 	A 3 character string, one of 
			BOT	 	Stock Purchase
			SLD	 	Stock sale
			DIV		Dividend payment
			DEP		Deposit
			WDR		Withdrawal
Units	 - The number of units bought or sold to 4 decimal places
Asset	 - A four character string
  			- For BOT, SLD transactions, this is the stock purchased/sold
  			- For DIV		 transactions this will be the stock for which the dividend was issued
  			- For DEP, WDR	 transactions it will be ```CASH```
Price - Price per unit to 4 decimal places.  For ```CASH``` transactions this will always be 1.
 */

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private String account;
	private LocalDate date;
	private String txnType;
	private BigDecimal units;
	private String asset;
	private BigDecimal price;

	private boolean isValid;

	public Transaction() {

	}

	public Transaction(String account, LocalDate date, String txnType, BigDecimal units, String asset, BigDecimal price,
			boolean isValid) {
		super();
		this.account = account;
		this.date = date;
		this.txnType = txnType;
		this.units = units;
		this.asset = asset;
		this.price = price;
		this.isValid = isValid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "Transaction [account=" + account + ", date=" + date + ", txnType=" + txnType + ", units=" + units
				+ ", asset=" + asset + ", price=" + price + ", isValid=" + isValid + "]";
	}

}
