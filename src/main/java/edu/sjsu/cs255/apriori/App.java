package edu.sjsu.cs255.apriori;

import org.kohsuke.args4j.CmdLineParser;

import edu.sjsu.cs255.common.Options;
import edu.sjsu.cs255.structures.Dataset;
import edu.sjsu.cs255.structures.Itemset;

public class App 
{
    public static void main( String[] args )
    {
        Options o = new Options();
        CmdLineParser parser = new CmdLineParser(o);
        Dataset d = new Dataset();
        Itemset i = new Itemset(d);
        try {
			parser.parseArgument(args);
			d.loadFromFile(o.getInputFile());
			i.loadItemsFromData();
			System.out.println(i.generateFrequentItemsets(o.getMinSupport()).generateFrequentItemsets(2));

		} catch (Exception e) {
			System.out.println("In Exception");
			// TODO Auto-generated catch block
			System.out.println(e);
			System.exit(0);
		}
    }
}
