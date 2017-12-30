package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.TxnTypeHandler;

public class TxnTypeHandlerTest {

	TxnTypeHandler txnTypeHandler;

	@Before
	public void setUp() throws Exception {
		txnTypeHandler = new TxnTypeHandler();
	}

	@Test
	public void testTxnTypeHandlerInvalid() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "ABC", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertFalse("transaction type invalid ", transaction.isValid());
		assertEquals("transaction type should be null", null, transaction.getTxnType());
	}

	@Test
	public void testTxnTypeHandlerBOT() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "BOT", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertTrue("transaction type valid ", transaction.isValid());
		assertEquals("transaction type should be BOT", "BOT", transaction.getTxnType());
	}

	@Test
	public void testTxnTypeHandlerDIV() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "DIV", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertTrue("transaction type valid ", transaction.isValid());
		assertEquals("transaction type should be DIV", "DIV", transaction.getTxnType());
	}

	@Test
	public void testTxnTypeHandlerSLD() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "SLD", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertTrue("transaction type valid ", transaction.isValid());
		assertEquals("transaction type should be SLD", "SLD", transaction.getTxnType());
	}

	@Test
	public void testTxnTypeHandlerDEP() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "DEP", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertTrue("transaction type valid ", transaction.isValid());
		assertEquals("transaction type should be DEP", "DEP", transaction.getTxnType());
	}

	@Test
	public void testTxnTypeHandlerWDR() {
		String[] attributes = new String[] { "NEAB0001", "20170301", "WDR", "5000", "1", "CASH" };
		Transaction transaction = new Transaction();
		txnTypeHandler.process(attributes, transaction);
		assertTrue("transaction type valid ", transaction.isValid());
		assertEquals("transaction type should be WDR", "WDR", transaction.getTxnType());
	}
}
