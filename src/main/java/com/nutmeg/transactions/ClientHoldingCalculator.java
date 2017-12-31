package com.nutmeg.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.beans.TransactionKey;
import com.nutmeg.transactions.handlers.input.AccountHandler;
import com.nutmeg.transactions.handlers.input.AssetHandler;
import com.nutmeg.transactions.handlers.input.DateHandler;
import com.nutmeg.transactions.handlers.input.IInputHandler;
import com.nutmeg.transactions.handlers.input.LineHandler;
import com.nutmeg.transactions.handlers.input.PriceHandler;
import com.nutmeg.transactions.handlers.input.TxnTypeHandler;
import com.nutmeg.transactions.handlers.input.UnitHandler;
import com.nutmeg.transactions.handlers.txn.BalanceHandler;
import com.nutmeg.transactions.handlers.txn.BotTxnHandler;
import com.nutmeg.transactions.handlers.txn.DepTxnHandler;
import com.nutmeg.transactions.handlers.txn.DivTxnHandler;
import com.nutmeg.transactions.handlers.txn.ITxnHandler;
import com.nutmeg.transactions.handlers.txn.SldTxnHandler;
import com.nutmeg.transactions.handlers.txn.WdrTxnHandler;

public class ClientHoldingCalculator implements HoldingCalculator {

	public Map<String, List<Holding>> calculateHoldings(File transactionFile, LocalDate date) {
		Map<String, List<Holding>> transactionMap = new TreeMap<String, List<Holding>>();
		Map<TransactionKey, Holding> holdingMap = new HashMap<TransactionKey, Holding>();

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
		ITxnHandler balanceHandler = new BalanceHandler();

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
						balanceHandler.processHere(holdingMap, transaction);
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

	private void setUptransactionMap(Map<String, List<Holding>> transactionMap, Map<TransactionKey, Holding> holdingMap) {
		for (Map.Entry<TransactionKey, Holding> entry : holdingMap.entrySet()) {
			TransactionKey holdingKey = entry.getKey();
			String account = holdingKey.getAccount();
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

}
