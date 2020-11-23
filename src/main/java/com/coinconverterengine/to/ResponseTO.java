package com.coinconverterengine.to;

import java.util.HashMap;
import java.util.Map;

public class ResponseTO {
	
	private Map<String,Integer> coinsRequiredByType = new HashMap<String, Integer>();

	public Map<String, Integer> getCoinsRequiredByType() {
		return coinsRequiredByType;
	}

	public void setCoinsRequiredByType(Map<String, Integer> coinsRequiredByType) {
		this.coinsRequiredByType = coinsRequiredByType;
	}
	
	@Override
	public String toString() {
		return "ResponseTO [coinsRequiredByType=" + coinsRequiredByType.toString() + "]";
	}
}
