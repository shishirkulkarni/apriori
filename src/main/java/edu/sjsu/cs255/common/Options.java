package edu.sjsu.cs255.common;

import java.io.File;
import org.kohsuke.args4j.Option;

public class Options {
	
	@Option(name = "-f", aliases = "--file", metaVar = "FILE", usage="Input Dataset")
	private File inputFile = null;
	
	@Option(name = "-cat", aliases = "--catalog-file", metaVar = "FILE", usage = "Input Data Catalog")
	private File catalogFile = null;
	
	@Option(name = "-ms", aliases = "--min-support-count", metaVar = "INTEGER", usage = "Minimum Support Count")
	private int minSupport = 2;
	
	@Option(name = "-o", aliases = "--output-file", metaVar = "FILE", usage = "Output file")
	private File outputFile = null;

	@Option(name = "-c", aliases = "--confidence", metaVar = "INTEGER", usage = "Confidence")
	private int confidence = 30;
	
	@Option(name = "-g", aliases = "--graph", metaVar = "FILE", usage="Graph file")
	private File graphFile = null;
	
	public File getInputFile() {
		return inputFile;
	}

	public File getCatalogFile() {
		return catalogFile;
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
	
	public File getGraphFile() {
		return graphFile;
	}
	
}
