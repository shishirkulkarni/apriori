package edu.sjsu.cs255.apriori;

import java.util.ArrayList;

import org.kohsuke.args4j.CmdLineParser;

import edu.sjsu.cs255.common.Options;
import edu.sjsu.cs255.structures.AssociationRules;
import edu.sjsu.cs255.structures.DataCatalog;
import edu.sjsu.cs255.structures.Dataset;
import edu.sjsu.cs255.structures.Itemset;
import edu.sjsu.cs255.structures.NetworkGraph;
import edu.sjsu.cs255.structures.PersonCatalog;
import edu.sjsu.cs255.structures.Recommendations;

public class App 
{
    public static void main( String[] args )
    {
        Options o = new Options();
        CmdLineParser parser = new CmdLineParser(o);
        Dataset d = new Dataset();
        Itemset i = new Itemset(d);
        DataCatalog catalog = new DataCatalog();
        PersonCatalog personNames = new PersonCatalog();
        
        try {
			parser.parseArgument(args);
			d.loadFromFile(o.getInputFile());
			catalog.loadFromFile(o.getCatalogFile());
			i.loadItemsFromData(o.getMinSupport());
			personNames.loadFromFile(o.getPersonCatalogFile());
			
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
			rules.printRules(catalog);
			
			NetworkGraph g = new NetworkGraph();
			g.loadFromFile(o.getGraphFile());
			
			System.out.println("Product Recommendations for:");
			Recommendations reco = new Recommendations();
			reco.generateRecommendations(g, rules, d);
			reco.printRecommendations(catalog, personNames);
            
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
//			System.out.println(e);
			System.exit(0);
		}
    }
}
