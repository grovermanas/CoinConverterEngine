package com.coinconverterengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coinconverterengine.business.CalculateChangeBusinessService;
import com.coinconverterengine.business.impl.CalculateChangeBusinessServiceImpl;
import com.coinconverterengine.configuration.CoinCountProperties;

@Configuration
public class TestConfiguration {
	
	@Bean
	public CalculateChangeBusinessService calculateChangeBusinessService() {
		return new CalculateChangeBusinessServiceImpl();
	}
	
	@Bean
	public CoinCountProperties coinCountProperties() {
		return new CoinCountProperties();
	}

}
