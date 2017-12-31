package com.nutmeg.transactions.handlers.txn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.beans.TransactionKey;

public class BalanceHandlerTest {
	BalanceHandler balanceHandler;

	@Before
	public void setUp() throws Exception {
		balanceHandler = new BalanceHandler();
	}

	@Test
	public void testBalanceHandler() {
		Map<TransactionKey, Holding> holdingMap = new HashMap<TransactionKey, Holding>();
		holdingMap.put(new TransactionKey("NEAA0000", "CASH"), new Holding("CASH", 100));
		holdingMap.put(new TransactionKey("NEAA0000", "VUSA"), new Holding("VUSA", 30.0));

		Transaction transaction = new Transaction("NEAA0000", LocalDate.of(2017, 01, 01), "SLD", new BigDecimal("30"),
				"VUSA", new BigDecimal("1"), true);

		balanceHandler.process(holdingMap, transaction);

		Holding holding = holdingMap.get(new TransactionKey("NEAA0000", "CASH"));
		assertTrue("holding map did not have key NEAA0000 CASH", null != holding);
		assertEquals("NEAA0000 CASH should have holding of ", "CASH", holding.getAsset());
		assertEquals("NEAA0000 CASH should have holding of ", 100, holding.getHolding(), 0.1);

		holding = holdingMap.get(new TransactionKey("NEAA0000", "VUSA"));
		assertTrue("holding map did not have key NEAA0000 VUSA", null == holding);

	}

}
