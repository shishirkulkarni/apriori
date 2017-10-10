package edu.sjsu.cs255.apriori;

import java.io.IOException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import edu.sjsu.cs255.common.Options;
import edu.sjsu.cs255.exceptions.DuplicateTransactionException;
import edu.sjsu.cs255.exceptions.InvalidFormatException;
import edu.sjsu.cs255.structures.Dataset;
import edu.sjsu.cs255.structures.Itemset;

public class App 
{
    public static void main( String[] args )
    {
        Options o = new Options();
        CmdLineParser parser = new CmdLineParser(o);
        Dataset d = new Dataset();
        Itemset i = new Itemset();
        
        try {
			parser.parseArgument(args);
			d.loadFromFile(o.getInputFile());
			i.loadItemsFromData(d);
			System.out.println(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(0);
		}
    }
}
