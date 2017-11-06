package edu.sjsu.cs255.common;

import java.io.File;
import org.kohsuke.args4j.Option;

public class Options {
	
	@Option(name = "-f", aliases = "--file", metaVar = "FILE", usage="Input Dataset")
	private File inputFile = null;
	
	@Option(name = "-ms", aliases = "--min-support-count", metaVar = "INTEGER", usage = "Minimum Support Count")
	private int minSupport = 2;
	
	@Option(name = "-o", aliases = "--output-file", metaVar = "FILE", usage = "Output file")
	private File outputFile = null;

	@Option(name = "-c", aliases = "--confidence", metaVar = "INTEGER", usage = "Confidence")
	private int confidence = 60;
	
	public File getInputFile() {
		return inputFile;
	}
	
	public int getMinSupport() {
		return minSupport;
	}
	
	public File getOutputFile() {
		return outputFile;
	}
	
	public int getConfidence() {
		return confidence;
	}	
	
}
