package edu.sjsu.cs255.exceptions;

public class DuplicateTransactionException extends Exception {
	public DuplicateTransactionException(String message) {
		super(message);
	}
}
