package com.nutmeg.transactions.handlers.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.nutmeg.transactions.beans.Transaction;

public class DateHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
			LocalDate date = LocalDate.parse(attributes[1], formatter);
			transaction.setValid(true);
			transaction.setDate(date);
		} catch (DateTimeParseException ex) {
			transaction.setValid(false);
			transaction.setDate(null);
		}
		if (transaction.isValid() && handler != null) {
			handler.process(attributes, transaction);
		}
	}

}
