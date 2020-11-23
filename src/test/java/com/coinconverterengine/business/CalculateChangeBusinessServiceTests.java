package com.coinconverterengine.business;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coinconverterengine.config.TestConfiguration;
import com.coinconverterengine.configuration.CoinCountProperties;
import com.coinconverterengine.enums.CoinTypeEnumeration;
import com.coinconverterengine.to.ResponseTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class CalculateChangeBusinessServiceTests {
	
	@Autowired
	private CalculateChangeBusinessService calculateChangeBusinessServiceImpl;
	
	@Autowired
	private CoinCountProperties coinCountProperties;
	
	@BeforeEach
	public void setup() {
		//coinCountProperties.getCoinTypeByCountMap().put(CoinTypeEnumeration.HALF.name(), CoinTypeEnumeration.HALF.getDenomination());
		coinCountProperties.getCoinCount().put(CoinTypeEnumeration.QUARTER.name(), 100);
		coinCountProperties.getCoinCount().put(CoinTypeEnumeration.DIME.name(), 100);
		coinCountProperties.getCoinCount().put(CoinTypeEnumeration.NICKEL.name(), 100);
		coinCountProperties.getCoinCount().put(CoinTypeEnumeration.PENNY.name(), 100);
	}
	
	@Test
	public void calculateChangeTestMinimumValidStep() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(21, true);
		assertEquals(84, response.getCoinsRequiredByType().get(CoinTypeEnumeration.QUARTER.name()));
		ResponseTO response1 = calculateChangeBusinessServiceImpl.calculateChange(20, true);
		assertEquals(16, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.QUARTER.name()));
		assertEquals(100, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.DIME.name()));
		assertEquals(100, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.NICKEL.name()));
		assertEquals(100, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.PENNY.name()));
	}
	
	@Test
	public void calculateChangeTestMinimumValid() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(26, true);
		assertEquals(100, response.getCoinsRequiredByType().get(CoinTypeEnumeration.QUARTER.name()));
		assertEquals(10, response.getCoinsRequiredByType().get(CoinTypeEnumeration.DIME.name()));
	}
	
	@Test
	public void calculateChangeTestMinimumValidExcess() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(50, true);
		assertNull(response);
	}
	
	@Test
	public void calculateChangeTestMinimumInValid() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(-21, true);
		assertNull(response);
	}
	
	@Test
	public void calculateChangeTestMaximumValidStep() {
		ResponseTO response0 = calculateChangeBusinessServiceImpl.calculateChange(1, false);
		assertEquals(100, response0.getCoinsRequiredByType().get(CoinTypeEnumeration.PENNY.name()));
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(6, false);
		assertEquals(100, response.getCoinsRequiredByType().get(CoinTypeEnumeration.NICKEL.name()));
		assertEquals(10, response.getCoinsRequiredByType().get(CoinTypeEnumeration.DIME.name()));
		ResponseTO response1 = calculateChangeBusinessServiceImpl.calculateChange(15, false);
		assertEquals(90, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.DIME.name()));
		assertEquals(24, response1.getCoinsRequiredByType().get(CoinTypeEnumeration.QUARTER.name()));
		ResponseTO response2 = calculateChangeBusinessServiceImpl.calculateChange(19, false);
		assertEquals(76, response2.getCoinsRequiredByType().get(CoinTypeEnumeration.QUARTER.name()));
	}
	
	@Test
	public void calculateChangeTestMaximumValid() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(6, false);
		assertEquals(100, response.getCoinsRequiredByType().get(CoinTypeEnumeration.PENNY.name()));
		assertEquals(100, response.getCoinsRequiredByType().get(CoinTypeEnumeration.NICKEL.name()));
	}
	
	@Test
	public void calculateChangeTestMaximumInValid() {
		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(0, false);
		assertNull(response);
	}


}
