package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.UnitHandler;

public class UnitHandlerTest {

	UnitHandler unitHandler;

	@Before
	public void setUp() throws Exception {
		unitHandler = new UnitHandler();
	}

	@Test
	public void testUnitHandlerInvalidUnit() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "BOT", "XX", "99.99", "CASH" };
		Transaction transaction = new Transaction();
		unitHandler.process(attributes, transaction);
		assertFalse("unit invalid ", transaction.isValid());
		assertEquals("unit should be zero", new BigDecimal("0"), transaction.getUnits());

	}

	@Test
	public void testUnitHandlerValidUnit() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "BOT", "100", "99.99", "CASH" };
		Transaction transaction = new Transaction();
		unitHandler.process(attributes, transaction);
		assertTrue("unit valid ", transaction.isValid());
		assertEquals("unit should be 100", new BigDecimal("100"), transaction.getUnits());

	}
}
