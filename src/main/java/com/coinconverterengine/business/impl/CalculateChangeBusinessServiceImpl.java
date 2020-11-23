package com.coinconverterengine.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coinconverterengine.business.CalculateChangeBusinessService;
import com.coinconverterengine.configuration.CoinCountProperties;
import com.coinconverterengine.enums.CoinTypeEnumeration;
import com.coinconverterengine.to.CoinTO;
import com.coinconverterengine.to.ResponseTO;

@Component("calculateChangeBusinessServiceImpl")
public class CalculateChangeBusinessServiceImpl implements CalculateChangeBusinessService {

	@Autowired
	private CoinCountProperties coinCountProperties;

	@Override
	public ResponseTO calculateChange(int inputAmount, boolean calculateMinimum) {

		ResponseTO response = null;

		Map<String,Integer> coinsRequiredByType = new HashMap<String, Integer>();

		Set<String> coinTypes = coinCountProperties.getCoinCount().keySet();


		Integer amountLeft = inputAmount*100;

		if(validateInput(amountLeft, coinCountProperties.getCoinCount())) {

			response = new ResponseTO();

			if(calculateMinimum) {
				for(String s : coinTypes) {

					CoinTypeEnumeration coinTypeEnum = CoinTypeEnumeration.valueOf(s); coinsRequiredByType.put(s,
							calculateCoins(amountLeft, coinTypeEnum)); 
					amountLeft = amountLeft(amountLeft, coinsRequiredByType.get(s), coinTypeEnum);
					if(amountLeft == 0) {
						break;
					}
				}
			}else {

				List<String> coinTypeList = new
						ArrayList<String>(coinTypes);
				Collections.reverse(coinTypeList);

				for(String s : coinTypeList) {
					CoinTypeEnumeration coinTypeEnum = CoinTypeEnumeration.valueOf(s); 
					coinsRequiredByType.put(s,calculateCoins(amountLeft, coinTypeEnum)); 
					amountLeft = amountLeft(amountLeft, coinsRequiredByType.get(s), coinTypeEnum);

				}
			}
			coinsRequiredByType.remove(null);
			response.setCoinsRequiredByType(coinsRequiredByType);
		}
		return response;

	}

	private int calculateCoins(int amountLeft, CoinTypeEnumeration coinTypeEnum) {

		int coinCountLeft = coinCountProperties.getCoinCount().get(coinTypeEnum.name());

		int coinsRequired = amountLeft/coinTypeEnum.getDenomination() <= coinCountLeft ? amountLeft/coinTypeEnum.getDenomination() 
				: coinCountLeft;
		coinCountLeft = coinCountLeft - coinsRequired;
		coinCountProperties.getCoinCount().put(coinTypeEnum.name(), coinCountLeft);
		return coinsRequired;
	}

	private int amountLeft(int amountLeft, int coinsRequired, CoinTypeEnumeration coinTypeEnum) {
		return amountLeft - coinsRequired*coinTypeEnum.getDenomination();
	}


	private boolean validateInput(int amount, Map<String, Integer> coinTypesMap) {
		boolean isInputValid = true;
		int centsAvailable = 0;
		Set<String> coinTypes = coinTypesMap.keySet();
		for(String s : coinTypes) {
			centsAvailable = centsAvailable + coinTypesMap.get(s)*CoinTypeEnumeration.valueOf(s).getDenomination();
		}
		/*
		 * int centsAvailable = coinCountProperties.getQuarterCount()*25 +
		 * coinCountProperties.getDimeCount()*10 +
		 * coinCountProperties.getNickelCount()*5 + coinCountProperties.getPennyCount();
		 */
		System.out.println("Available cents :: " + centsAvailable);
		if(centsAvailable == 0) {
			System.out.println("No Coins available to tender change, system will exit" + centsAvailable);
			System.exit(0);
		}
		if(amount <= 0 ) {
			isInputValid = false;
			System.out.println("Invalid input :: amount entered is less than 0");
		}
		if(amount > centsAvailable) {
			isInputValid = false;
			System.out.println("Invalid input :: amount entered is more than available coins");
		}

		return isInputValid;
	}



}
