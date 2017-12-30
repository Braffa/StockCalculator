package com.nutmeg.transactions.beans;

public class Holding implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String asset;
	private double holding;

	public Holding() {

	}

	public Holding(String asset, double holding) {
		super();
		this.asset = asset;
		this.holding = holding;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public double getHolding() {
		return holding;
	}
	
	public String getHoldingAsString() {
		String s = String.format("%.4f", holding);
		return s;
	}

	public void setHoldings(double holding) {
		this.holding = holding;
	}

	public String toString() {
		return getAsset() + ":\t" + getHolding();
	}
}
