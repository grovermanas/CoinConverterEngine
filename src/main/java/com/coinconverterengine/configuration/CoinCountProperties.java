package com.coinconverterengine.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.coinconverterengine.to.CoinTO;

@ConfigurationProperties(prefix = "")
@Component
public class CoinCountProperties {
	
	Map<String,Integer> coinCount = new LinkedHashMap<String, Integer>();

	public Map<String, Integer> getCoinCount() {
		return coinCount;
	}
	
	
}
