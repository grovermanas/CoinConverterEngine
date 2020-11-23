package com.coinconverterengine;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.coinconverterengine.business.CalculateChangeBusinessService;
import com.coinconverterengine.configuration.CoinCountProperties;
import com.coinconverterengine.to.ResponseTO;

@SpringBootApplication
@EnableConfigurationProperties(CoinCountProperties.class)
public class CoinConverterEngineApplication implements CommandLineRunner {

	@Autowired
	private CalculateChangeBusinessService calculateChangeBusinessServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(CoinConverterEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the amount to be changed and if minimum coins are required");
		Integer amount=null;
		boolean calculateMinimum = true;

		while((amount = scanner.nextInt()) > 0) {
			if(scanner.hasNextBoolean()) {
			calculateMinimum = scanner.nextBoolean();
			}
			ResponseTO response = calculateChangeBusinessServiceImpl.calculateChange(amount, calculateMinimum);
			if(null != response) {
				System.out.println(response.toString());
			}
			System.out.println("Enter the amount to be changed");
		}

	}

}
