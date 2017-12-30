package com.nutmeg.transactions.handlers.txn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Holding;
import com.nutmeg.transactions.beans.Transaction;

public class WdrTxnHandlerTest {
	WdrTxnHandler wdrTxnHandler;

	@Before
	public void setUp() throws Exception {
		wdrTxnHandler = new WdrTxnHandler();
	}

	@Test
	public void testDepTxnHandler() {
		Map<String, Holding> holdingMap = new TreeMap<String, Holding>();
		holdingMap.put("NEAA0000CASH", new Holding("CASH", 100));
		holdingMap.put("NEAA0000VUSA", new Holding("VUSA", 30.0));	
		
		Transaction transaction = new Transaction("NEAA0000", LocalDate.of(2017, 01, 01),"WDR", new BigDecimal("25"), "CASH",new BigDecimal("1"),true);

		wdrTxnHandler.process(holdingMap, transaction);
		
		Holding holding = holdingMap.get("NEAA0000CASH");
		assertTrue("holding map did not have key NEAA0000CASH", null != holding);
		assertEquals("NEAA0000CASH should have holding of ", "CASH", holding.getAsset());
		assertEquals("NEAA0000CASH should have holding of ", 75, holding.getHolding(), 0.1);

		holding = holdingMap.get("NEAA0000VUSA");
		assertTrue("holding map did not have key NEAA0000VUSA", null != holding);
		assertEquals("NEAA0000VUSA should have holding of ", "VUSA", holding.getAsset());
		assertEquals("NEAA0000VUSA should have holding of ", 30, holding.getHolding(), 0.1);

	}

}
