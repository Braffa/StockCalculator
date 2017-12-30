package com.nutmeg.transactions.handlers.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Transaction;

public class DateHandlerTest {
	DateHandler dateHandler;

	@Before
	public void setUp() throws Exception {
		dateHandler = new DateHandler();
	}

	@Test
	public void testDateHandlerValidDate() {
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		LocalDate date = LocalDate.parse("20170301", formatter);
		String [] attributes = new String[] {"NEAB0001", "20170301", "WDR", "5000", "1", "CASH"};
		Transaction transaction = new Transaction();
		dateHandler.process(attributes, transaction);
		assertTrue("Valid date ", transaction.isValid());
		assertEquals("Valid date", date, transaction.getDate());
	}

	@Test
	public void testDateHandlerInValidDate() {
		String [] attributes = new String[] {"NEAB0001", "2017030", "WDR", "5000", "1", "CASH"};
		Transaction transaction = new Transaction();
		dateHandler.process(attributes, transaction);
		assertFalse("Invalid date ", transaction.isValid());
		assertEquals("Valid date", null, transaction.getDate());
	}
}
