package edu.sjsu.cs255.apriori;

import java.util.ArrayList;

import org.kohsuke.args4j.CmdLineParser;

import edu.sjsu.cs255.common.Options;
import edu.sjsu.cs255.structures.AssociationRules;
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
			i.loadItemsFromData(o.getMinSupport());
			
			ArrayList<Itemset> freqItems = new ArrayList<Itemset>();
			freqItems.add(0, i);
			
			for(int j = 1; ; j++) {
				Itemset k = freqItems.get(j - 1).generateNextFrequentItemsets(o.getMinSupport());
				k.prune(freqItems.get(j - 1));
				if(k.size()==0)
					break;
				freqItems.add(j,k);
			}
			
			AssociationRules rules = new AssociationRules(freqItems);
			rules.generate(o.getConfidence());
			System.out.println(rules);
            
		} catch (Exception e) {
			System.out.println("In Exception");
			// TODO Auto-generated catch block
			System.out.println(e);
			System.exit(0);
		}
    }
}
