package com.nutmeg.transactions;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import com.nutmeg.transactions.beans.Holding;

public class StockCalculator {
	
	public static void main(String[] args) {
		if (args.length == 0 || args.length != 2) {
			System.out.println("please enter a file name and a date");
			System.exit(0);
		}
		File transactionFile = new File(args[0]);
		if (!transactionFile.exists()) {
			System.out.println("File " + args[0] + " does not exist");
			System.exit(0);
		}
		LocalDate date = LocalDate.now();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
			date = LocalDate.parse(args[1], formatter);
		} catch (DateTimeParseException ex) {
			System.out.println("Invalid date entered " + args[1]);
			System.out.println("Please enter a date in format entered YYYYMMDD");
			System.exit(0);
		}
		ClientHoldingCalculator clientHoldingCalculator = new ClientHoldingCalculator();
		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);
		
		for (Map.Entry<String, List<Holding>> entry : transactionMap.entrySet()) {
			System.out.println(entry.getKey());
			for (Holding holding : entry.getValue()) {
				System.out.println("   " + holding.getAsset() + " " + holding.getHoldingAsString());
			}
		}
	}

}
