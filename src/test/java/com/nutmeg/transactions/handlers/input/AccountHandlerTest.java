package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.AccountHandler;

public class AccountHandlerTest {

	AccountHandler accountHandler;

	@Before
	public void setUp() throws Exception {
		accountHandler = new AccountHandler();
	}

	@Test
	public void testAccountHandlerValidAccount() {
		String [] attributes = new String[] {"NEAB0001", "20170301", "WDR", "5000", "1", "CASH"};
		Transaction transaction = new Transaction();
		accountHandler.process(attributes, transaction);
		assertTrue("Valid account number", transaction.isValid());
		assertEquals("Valid account number", "NEAB0001", transaction.getAccount());
	}

	@Test
	public void testAccountHandlerInValidAccount() {
		String [] attributes = new String[] {"NEAB00", "20170301", "WDR", "5000", "1", "CASH"};
		Transaction transaction = new Transaction();
		accountHandler.process(attributes, transaction);
		assertFalse("Valid account number", transaction.isValid());
		assertEquals("Valid account number", null, transaction.getAccount());
	}
}
