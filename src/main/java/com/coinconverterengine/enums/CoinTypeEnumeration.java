package com.coinconverterengine.enums;

public enum CoinTypeEnumeration {

	PENNY(1),
	DIME(10),
	NICKEL(5),
	QUARTER(25),
	HALF(50);

	private int denomination;

	CoinTypeEnumeration(final int denomination) {

		this.denomination = denomination;
	}



	public int getDenomination() {
		return this.denomination;
	}

}
