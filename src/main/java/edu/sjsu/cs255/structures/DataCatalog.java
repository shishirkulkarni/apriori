package edu.sjsu.cs255.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import edu.sjsu.cs255.config.Config;
import edu.sjsu.cs255.exceptions.DuplicateTransactionException;
import edu.sjsu.cs255.exceptions.InvalidFormatException;

public class DataCatalog {
	Map<Integer, String> dataCatalog;
	
	public DataCatalog() {
		this.dataCatalog = new HashMap<Integer, String>();
	}
	
	public void loadFromFile(File dataFile) throws InvalidFormatException, IOException, NumberFormatException, DuplicateTransactionException {
		dataCatalog.clear();// Remove any existing data
		
		BufferedReader reader = new BufferedReader(new FileReader(dataFile));
		
		String line;
		while((line = reader.readLine()) != null) {
			putIntoMap(line);
		}
	}
	
	private void putIntoMap(String line) throws NumberFormatException, DuplicateTransactionException {
		String []fields = line.split(":");	
		// Check for any duplicated items
		if(dataCatalog.get(Integer.parseInt(fields[0])) != null) {
			throw new DuplicateTransactionException("apriori: Catalog " + Integer.parseInt(fields[0]) + " is duplicated");
		}
		dataCatalog.put(Integer.parseInt(fields[0]), fields[1]);
	}
	
	public String getName(Integer itemNumber) {
		return dataCatalog.get(itemNumber);
	}
	
	public void clear() {
		dataCatalog.clear();
	}

}
