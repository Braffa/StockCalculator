package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.AccountHandler;
import com.nutmeg.transactions.handlers.input.AssetHandler;
import com.nutmeg.transactions.handlers.input.DateHandler;
import com.nutmeg.transactions.handlers.input.LineHandler;
import com.nutmeg.transactions.handlers.input.PriceHandler;
import com.nutmeg.transactions.handlers.input.TxnTypeHandler;
import com.nutmeg.transactions.handlers.input.UnitHandler;

public class ChainHandlerTest {

	LineHandler lineHandler;
	AccountHandler accountHandler;
	DateHandler dateHandler;
	TxnTypeHandler txnTypeHandler;
	PriceHandler priceHandler;
	UnitHandler unitHandler;
	AssetHandler assetHandler;

	@Before
	public void setUp() throws Exception {
		lineHandler = new LineHandler();
		accountHandler = new AccountHandler();
		dateHandler = new DateHandler();
		txnTypeHandler = new TxnTypeHandler();
		priceHandler = new PriceHandler();
		unitHandler = new UnitHandler();
		assetHandler = new AssetHandler();
		
		lineHandler.setHandler(accountHandler);
		accountHandler.setHandler(dateHandler);
		dateHandler.setHandler(txnTypeHandler);
		txnTypeHandler.setHandler(priceHandler);
		priceHandler.setHandler(unitHandler);
		unitHandler.setHandler(assetHandler);
	}

	@Test
	public void testChainingHandlers() {
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		LocalDate date = LocalDate.parse("20170301", formatter);
		String [] attributes = new String[] {"NEAB0001,20170301,WDR,1,5000.1234,CASH"};
		Transaction transaction = new Transaction();
		lineHandler.process(attributes, transaction);

		assertTrue("Valid transaction", transaction.isValid());
		assertEquals("Valid account number", "NEAB0001", transaction.getAccount());
		assertEquals("Valid date", date, transaction.getDate());
		assertEquals("Valid transaction type", "WDR", transaction.getTxnType());
		assertEquals("Valid price", new BigDecimal("5000.1234"), transaction.getPrice());
		assertEquals("Valid unit", new BigDecimal("1"), transaction.getUnits());
		assertEquals("Valid asset", "CASH", transaction.getAsset());
	}

}
