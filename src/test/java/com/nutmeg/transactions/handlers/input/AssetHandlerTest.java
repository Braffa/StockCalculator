package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.AssetHandler;

public class AssetHandlerTest {

	 AssetHandler assetHandler;

	@Before
	public void setUp() throws Exception {
		assetHandler = new AssetHandler();
	}

	@Test
	public void testAssetHandlerValidAsset() {
		String [] attributes = new String[] {"NEAB0001", "20170301", "WDR", "5000", "1", "CASH"};
		Transaction transaction = new Transaction();
		assetHandler.process(attributes, transaction);
		assertTrue("Valid asset", transaction.isValid());
		assertEquals("Valid asset", "CASH", transaction.getAsset());
	}

	@Test
	public void testAssetHandlerInValidAsset() {
		String [] attributes = new String[] {"NEAB0001", "20170301", "WDR", "5000", "1", "ABC"};
		Transaction transaction = new Transaction();
		assetHandler.process(attributes, transaction);
		assertFalse("invalid asset", transaction.isValid());
		assertEquals("asset should be null", null, transaction.getAsset());
	}
}
