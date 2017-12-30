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

public class BotTxnHandlerTest {
	BotTxnHandler botTxnHandler;

	@Before
	public void setUp() throws Exception {
		botTxnHandler = new BotTxnHandler();
	}
	@Test
	public void testBotTxnHandlerNoAssets() {
		
		Map<String, Holding> holdingMap = new TreeMap<String, Holding>();
		holdingMap.put("NEAA0000CASH", new Holding("CASH", 100));
		holdingMap.put("NEAA0000VUSA", new Holding("VUSA", 30.0));	
		
		Transaction transaction = new Transaction("NEAA0000", LocalDate.of(2017, 01, 01),"BOT", new BigDecimal("20"), "VUKE",new BigDecimal("2.123"),true);
		
		botTxnHandler.process(holdingMap, transaction);

		Holding holding = holdingMap.get("NEAA0000CASH");
		assertTrue("holding map did not have key NEAA0000CASH", null != holding);
		assertEquals("NEAA0000CASH should have holding of ", "CASH", holding.getAsset());
		assertEquals("NEAA0000CASH should have holding of ", 57.54, holding.getHolding(), 0.1);

		holding = holdingMap.get("NEAA0000VUKE");
		assertTrue("holding map did not have key NEAA0000VUKE", null != holding);
		assertEquals("NEAA0000VUKE should have holding of ", "VUKE", holding.getAsset());
		assertEquals("NEAA0000VUKE should have holding of ", 20, holding.getHolding(), 0.1);

	}
	@Test
	public void testBotTxnHandlerVUKE() {
		
		Map<String, Holding> holdingMap = new TreeMap<String, Holding>();
		holdingMap.put("NEAA0000CASH", new Holding("CASH", 100));
		holdingMap.put("NEAA0000VUKE", new Holding("VUKE", 0.0));
		holdingMap.put("NEAA0000VUSA", new Holding("VUSA", 30.0));	
		
		Transaction transaction = new Transaction("NEAA0000", LocalDate.of(2017, 01, 01),"BOT", new BigDecimal("20"), "VUKE",new BigDecimal("2.123"),true);
		
		botTxnHandler.process(holdingMap, transaction);

		Holding holding = holdingMap.get("NEAA0000CASH");
		assertTrue("holding map did not have key NEAA0000CASH", null != holding);
		assertEquals("NEAA0000CASH should have holding of ", "CASH", holding.getAsset());
		assertEquals("NEAA0000CASH should have holding of ", 57.54, holding.getHolding(), 0.1);

		holding = holdingMap.get("NEAA0000VUKE");
		assertTrue("holding map did not have key NEAA0000VUKE", null != holding);
		assertEquals("NEAA0000VUKE should have holding of ", "VUKE", holding.getAsset());
		assertEquals("NEAA0000VUKE should have holding of ", 20, holding.getHolding(), 0.1);

	}
	@Test
	public void testBotTxnHandlerUSA() {
		
		Map<String, Holding> holdingMap = new TreeMap<String, Holding>();
		holdingMap.put("NEAA0000CASH", new Holding("CASH", 100));
		holdingMap.put("NEAA0000VUKE", new Holding("VUKE", 0.0));
		holdingMap.put("NEAA0000VUSA", new Holding("VUSA", 10.0));	
		
		Transaction transaction = new Transaction("NEAA0000", LocalDate.of(2017, 01, 01),"BOT", new BigDecimal("30"), "VUSA",new BigDecimal("1.5"),true);
		
		botTxnHandler.process(holdingMap, transaction);

		Holding holding = holdingMap.get("NEAA0000CASH");
		assertTrue("holding map did not have key NEAA0000CASH", null != holding);
		assertEquals("NEAA0000CASH should have holding of ", "CASH", holding.getAsset());
		assertEquals("NEAA0000CASH should have holding of ", 55, holding.getHolding(), 0.1);

		holding = holdingMap.get("NEAA0000VUSA");
		assertTrue("holding map did not have key NEAA0000VUSA", null != holding);
		assertEquals("NEAA0000VUSA should have holding of ", "VUSA", holding.getAsset());
		assertEquals("NEAA0000VUSA should have holding of ", 40, holding.getHolding(), 0.1);

	}
}
