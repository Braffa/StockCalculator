package com.nutmeg.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.AccountHandler;
import com.nutmeg.transactions.handlers.input.AssetHandler;
import com.nutmeg.transactions.handlers.input.DateHandler;
import com.nutmeg.transactions.handlers.input.IInputHandler;
import com.nutmeg.transactions.handlers.input.LineHandler;
import com.nutmeg.transactions.handlers.input.PriceHandler;
import com.nutmeg.transactions.handlers.input.TxnTypeHandler;
import com.nutmeg.transactions.handlers.input.UnitHandler;
import com.nutmeg.transactions.handlers.txn.BotTxnHandler;
import com.nutmeg.transactions.handlers.txn.DepTxnHandler;
import com.nutmeg.transactions.handlers.txn.DivTxnHandler;
import com.nutmeg.transactions.handlers.txn.ITxnHandler;
import com.nutmeg.transactions.handlers.txn.SldTxnHandler;
import com.nutmeg.transactions.handlers.txn.WdrTxnHandler;

public class ClientHoldingCalculator implements HoldingCalculator {

	public Map<String, List<Holding>> calculateHoldings(File transactionFile, LocalDate date) {
		Map<String, List<Holding>> transactionMap = new TreeMap<String, List<Holding>>();
		Map<String, Holding> holdingMap = new TreeMap<String, Holding>();

		IInputHandler lineHandler = new LineHandler();
		IInputHandler accountHandler = new AccountHandler();
		IInputHandler dateHandler = new DateHandler();
		IInputHandler txnTypeHandler = new TxnTypeHandler();
		IInputHandler unitHandler = new UnitHandler();
		IInputHandler priceHandler = new PriceHandler();
		IInputHandler assetHandler = new AssetHandler();

		lineHandler.setHandler(accountHandler);
		accountHandler.setHandler(dateHandler);
		dateHandler.setHandler(txnTypeHandler);
		txnTypeHandler.setHandler(unitHandler);
		unitHandler.setHandler(priceHandler);
		priceHandler.setHandler(assetHandler);

		ITxnHandler botTxnHandler = new BotTxnHandler();
		ITxnHandler sldTxnHandler = new SldTxnHandler();
		ITxnHandler divTxnHandler = new DivTxnHandler();
		ITxnHandler depTxnHandler = new DepTxnHandler();
		ITxnHandler wdrTxnHandler = new WdrTxnHandler();

		botTxnHandler.setHandler(sldTxnHandler);
		sldTxnHandler.setHandler(divTxnHandler);
		divTxnHandler.setHandler(depTxnHandler);
		depTxnHandler.setHandler(wdrTxnHandler);

		try {
			Scanner in = new Scanner(new FileReader(transactionFile));
			while (in.hasNextLine()) {
				String line = in.nextLine().trim();
				Transaction transaction = new Transaction();
				lineHandler.process(new String[] { line }, transaction);
				if (transaction.isValid()) {
					if (!transaction.getDate().isAfter(date)) {
						botTxnHandler.processHere(holdingMap, transaction);
					}
				}
			}
			in.close();
			setUptransactionMap(transactionMap, holdingMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return transactionMap;
	}

	private void setUptransactionMap(Map<String, List<Holding>> transactionMap, Map<String, Holding> holdingMap) {
		for (Map.Entry<String, Holding> entry : holdingMap.entrySet()) {
			String holdingKey = entry.getKey();
			String account = holdingKey.substring(0, 8);
			if (transactionMap.containsKey(account)) {
				List<Holding> lOfHoldings = transactionMap.get(account);
				lOfHoldings.add(entry.getValue());
			} else {
				List<Holding> lOfHoldings = new ArrayList<Holding>();
				lOfHoldings.add(entry.getValue());
				transactionMap.put(account, lOfHoldings);
			}
		}
	}

/*	public static void main(String[] args) {
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
	}*/
}
