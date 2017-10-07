package edu.sjsu.cs255.common;

import java.io.File;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Options {
	String[] args;
	
	@Option(name="-f", usage="Input file")
	private File inputFile;
	
	public Options(String[] args) {
		this.args = args;
	}
	
	public void parse() {
		CmdLineParser parser = new CmdLineParser(this);
		
	}
}
