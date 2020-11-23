package com.coinconverterengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coinconverterengine.business.CalculateChangeBusinessService;
import com.coinconverterengine.to.ResponseTO;

@RestController
public class ChangeController {

	@Autowired
	private CalculateChangeBusinessService calculateChangeBusinessServiceImpl;

	@PutMapping("/calculateChange")
	public ResponseEntity<ResponseTO> calculateChange(@RequestParam Integer amount, @RequestParam(defaultValue = "1") boolean calculateMinimum) {

		ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(amount, calculateMinimum);

		if(null == response) {
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
