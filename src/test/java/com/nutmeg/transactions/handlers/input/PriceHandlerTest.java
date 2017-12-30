package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.PriceHandler;

public class PriceHandlerTest {

	PriceHandler priceHandler;

	@Before
	public void setUp() throws Exception {
		priceHandler = new PriceHandler();
	}

	@Test
	public void testPriceHandlerInvalidPrice() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "BOT", "100", "XX.XX", "CASH" };
		Transaction transaction = new Transaction();
		priceHandler.process(attributes, transaction);
		assertFalse("price invalid ", transaction.isValid());
		assertEquals("price should be zero", new BigDecimal("0"), transaction.getPrice());

	}

	@Test
	public void testPriceHandlerValidPrice() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "BOT", "100", "10.512", "CASH" };
		Transaction transaction = new Transaction();
		priceHandler.process(attributes, transaction);
		assertTrue("price valid ", transaction.isValid());
		assertEquals("price should be 10.512", new BigDecimal("10.512"), transaction.getPrice());

	}
}
