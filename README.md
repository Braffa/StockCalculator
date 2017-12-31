# StockCalculator

Design

For each record in the input file
	Validate the record using the chain of command design pattern
	    any invalid record will be ignored
	Valid record are processed throught another chain of responsibilty design pattern
	    process the transaction
	    add the transaction to a transient map of holdings keyed on account and asset
The transient map is finally parsed to the holding map keyed on account.     

To run the application

1. Copy the jar file to the local machine.
2. Open command prompt and navigate to the directory where the jar was saved
3. Key in the command "java -jar StockCalculator.jar path_to_input_file date 
4. The input file should have the suffix .csv
5. The date should be in the format YYYYMMDD 