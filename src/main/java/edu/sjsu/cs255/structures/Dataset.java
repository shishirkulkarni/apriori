package edu.sjsu.cs255.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import edu.sjsu.cs255.config.Config;
import edu.sjsu.cs255.exceptions.DuplicateTransactionException;
import edu.sjsu.cs255.exceptions.InvalidFormatException;

public class Dataset {
	Map<Integer, Set<Integer>> data;
	private Pattern transactionFormat;
	
	public Dataset() {
		this.data = new HashMap<Integer, Set<Integer>>();
	}
	
	public void loadFromFile(File dataFile) throws InvalidFormatException, IOException, NumberFormatException, DuplicateTransactionException {
		data.clear();// Remove any existing data
		
		BufferedReader reader = new BufferedReader(new FileReader(dataFile));
		
		String line;
		while((line = reader.readLine()) != null) {
			if(isValid(line)) {
				putIntoMap(line);
			} else {
				throw new InvalidFormatException("Line + \"" + line + "\" is not in parsable format");
			}
		}
	}
	
	private boolean isValid(String line) {
		return transactionFormat.matches(Config.patternFormat, line);
	}
	
	private void putIntoMap(String line) throws NumberFormatException, DuplicateTransactionException {
		String []fields = line.split(":");
		Set<Integer> transactions = new HashSet<Integer>();
		
		// Check for any duplicated transactions
		if(data.get(Integer.parseInt(fields[0])) != null) {
			throw new DuplicateTransactionException("apriori: Transaction " + Integer.parseInt(fields[0]) + " is duplicated");
		}
		
		for(String value: fields[1].split(",")) {
			transactions.add(Integer.parseInt(value.trim()));
		}
		
		data.put(Integer.parseInt(fields[0]), transactions);
	}
	
	@Override
	public String toString() {
		Iterator<Integer> it = data.keySet().iterator();
		StringBuilder s = new StringBuilder();
		while(it.hasNext()) {
			Integer key = it.next();
			s.append(key + ": ");
			s.append(data.get(key).toString() + "\n");
		}
		
		return s.toString();
	}
	
	public void clear() {
		data.clear();
	}
	
	public Set<Integer> getShoppingList(int ID) {
		return data.get(ID);
	}

}
