package com.coinconverterengine.business;

import com.coinconverterengine.to.ResponseTO;

public interface CalculateChangeBusinessService {

	public ResponseTO calculateChange(int amount , boolean calculateMinimum);
	
}
