package com.nutmeg.transactions.handlers.input;

import java.util.Scanner;

import com.nutmeg.transactions.beans.Transaction;

public class LineHandler extends AbstractInputHandler {

	public void process(String[] attributes, Transaction transaction) {
		String[] fields = new String[6];
		Scanner scan = new Scanner(attributes[0]);
		scan.useDelimiter(",");
		int index = 0;
		try {
			while (scan.hasNext()) {
				String field = scan.next();
				fields[index] = field;
				index++;
			}
		} catch (IndexOutOfBoundsException e) {
			transaction.setValid(false);
		} finally {
			scan.close();
		}
		if (index != 6) {
			transaction.setValid(false);
		} else {
			transaction.setValid(true);
			if (handler != null) {
				handler.process(fields, transaction);
			}
		}
	}

}
