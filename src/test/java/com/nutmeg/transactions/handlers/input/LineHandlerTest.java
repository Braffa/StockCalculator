package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;
import com.nutmeg.transactions.handlers.input.LineHandler;

public class LineHandlerTest {

	LineHandler lineHandler;

	@Before
	public void setUp() throws Exception {
		lineHandler = new LineHandler();
	}
	@Test
	public void testLineHandlerValidLine() {
		String [] attributes = new String[] {"NEAB0001,20170301,WDR,5000,1,CASH"};
		Transaction transaction = new Transaction();
		lineHandler.process(attributes, transaction);
		assertTrue("input has Valid number of attributes ", transaction.isValid());
	}

	@Test
	public void testLineHandlerInValidLine() {
		String [] attributes = new String[] {"NEAB0001,CASH"};
		Transaction transaction = new Transaction();
		lineHandler.process(attributes, transaction);
		assertFalse("input does not have Valid number of attributes ", transaction.isValid());
	}

}
