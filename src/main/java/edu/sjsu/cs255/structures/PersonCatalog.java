package edu.sjsu.cs255.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.sjsu.cs255.exceptions.DuplicateTransactionException;
import edu.sjsu.cs255.exceptions.InvalidFormatException;

public class PersonCatalog {

	Map<Integer, String> personCatalog;

	public PersonCatalog() {
		this.personCatalog = new HashMap<Integer, String>();
	}

	public void loadFromFile(File dataFile)
			throws InvalidFormatException, IOException, NumberFormatException, DuplicateTransactionException {
		personCatalog.clear();// Remove any existing data

		BufferedReader reader = new BufferedReader(new FileReader(dataFile));

		String line;
		while ((line = reader.readLine()) != null) {
			putIntoMap(line);
		}
	}

	private void putIntoMap(String line) throws NumberFormatException, DuplicateTransactionException {
		String[] fields = line.split(":");
		// Check for any duplicated items
		if (personCatalog.get(Integer.parseInt(fields[0])) != null) {
			throw new DuplicateTransactionException(
					"apriori: Catalog " + Integer.parseInt(fields[0]) + " is duplicated");
		}
		personCatalog.put(Integer.parseInt(fields[0]), fields[1]);
	}

	public String getName(Integer personID) {
		return personCatalog.get(personID);
	}

	public void clear() {
		personCatalog.clear();
	}

}
