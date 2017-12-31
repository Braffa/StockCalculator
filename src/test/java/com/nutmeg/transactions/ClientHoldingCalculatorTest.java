package com.nutmeg.transactions;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.nutmeg.transactions.beans.Holding;

public class ClientHoldingCalculatorTest {

	ClientHoldingCalculator clientHoldingCalculator;

	@Before
	public void setUp() throws Exception {
		clientHoldingCalculator = new ClientHoldingCalculator();
	}

	@Test
	public void testValidInputFile() {

		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/example.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be GILS ", "GILS", holding.getAsset());
		assertEquals("GILS holding should be 10.5120", "10.5120", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 17.6848", "17.6848", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(3);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());
	}

	@Test
	/*
	 * The holding GILS has been set with an invalid input record and therefore
	 * should be ignored
	 */
	public void testInvalidInputRecord() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidInputRecord.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has been set with an invalid account number and
	 * therefore should be ignored
	 */
	public void testInvalidAccountNumber() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidAccountNumber.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has been set with an invalid date number and therefore
	 * should be ignored
	 */
	public void testInvalidDate() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidDate.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has an invalid transaction type and therefore should be
	 * ignored
	 */
	public void testInvalidTxnType() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidTxnType.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has invalid units and therefore should be ignored
	 */
	public void testInvalidUnits() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidUnits.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has invalid price and therefore should be ignored
	 */
	public void testInvalidPrice() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidPrice.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The holding GILS has been changed to an invalid asset and therefore
	 * should be ignored
	 */
	public void testInvalidAsset() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleInvalidAsset.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAA0000");
		assertTrue("NEAA0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 52.7424", "52.7424", holding.getHoldingAsString());

		holding = ListOfHoldings.get(1);
		assertEquals("asset should be VUSA ", "VUSA", holding.getAsset());
		assertEquals("VUSA holding should be 10.0000", "10.0000", holding.getHoldingAsString());

		holding = ListOfHoldings.get(2);
		assertEquals("asset should be VUKE ", "VUKE", holding.getAsset());
		assertEquals("VUKE holding should be 20.0000", "20.0000", holding.getHoldingAsString());

		ListOfHoldings = transactionMap.get("NEAB0001");
		assertTrue("NEAB0001 Holdings should not be null ", ListOfHoldings != null);

		holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 10000.0000", "10000.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The account NEAC0002 will have a zero balance
	 */
	public void testCashBalanceZero() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleCashBalanceZero.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAC0002");
		assertTrue("NEAC0000 Holdings should not be null ", ListOfHoldings != null);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 0.0000", "0.0000", holding.getHoldingAsString());

	}

	@Test
	/*
	 * The account NEAC0002 will have no asset VUSA and so 
	 * there will not be an object in ListOfHoldings
	 */
	public void testZeroHolding() {
		File transactionFile = new File(
				"/Users/dave/workspaces/eclipseNeon/StockCalculator/src/test/resources/exampleZeroholding.csv");

		LocalDate date = LocalDate.of(2017, 02, 01);

		Map<String, List<Holding>> transactionMap = clientHoldingCalculator.calculateHoldings(transactionFile, date);

		List<Holding> ListOfHoldings = transactionMap.get("NEAC0002");
		assertTrue("NEAC0000 Holdings should not be null ", ListOfHoldings != null);

		assertTrue("NEAC0000 Holdings should only have 1 entry", ListOfHoldings.size() == 1);

		Holding holding = ListOfHoldings.get(0);
		assertEquals("asset should be CASH ", "CASH", holding.getAsset());
		assertEquals("CASH holding should be 0.0000", "0.0000", holding.getHoldingAsString());

	}
}
