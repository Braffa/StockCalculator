package com.nutmeg.transactions.beans;

//public class TransactionKey implements Comparable<TransactionKey> {
public class TransactionKey {
	private String account;
	private String asset;

	public TransactionKey(String account, String asset) {
		super();
		this.account = account;
		this.asset = asset;
	}

	public String getAccount() {
		return account;
	}

	public String getAsset() {
		return asset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((asset == null) ? 0 : asset.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionKey other = (TransactionKey) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (asset == null) {
			if (other.asset != null)
				return false;
		} else if (!asset.equals(other.asset))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransactionKey [account=" + account + ", asset=" + asset + "]";
	}
}
