package com.coinconverterengine.to;

import com.coinconverterengine.enums.CoinTypeEnumeration;

public class CoinTO {
	
	private int coinCount;
	private CoinTypeEnumeration coinType;
	
	public int getCoinCount() {
		return coinCount;
	}
	
	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}
	public CoinTypeEnumeration getCoinType() {
		return coinType;
	}
	public void setCoinType(CoinTypeEnumeration coinType) {
		this.coinType = coinType;
	}
	

}
