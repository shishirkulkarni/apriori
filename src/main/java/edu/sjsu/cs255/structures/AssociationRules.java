package edu.sjsu.cs255.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Sets;
import edu.sjsu.cs255.exceptions.EmptyItemSetException;

public class AssociationRules {

	//Get the frequent item sets
	//Generate non-empty item sets from frequent item sets (say I)
	//If 's' is there, customer should be shown (recommends) (I-s)
	//If (support count of I)/(support count of S) >= min_confidence (input)
	private List<Itemset> freqItems;
	private Map<Set<Integer>,Set<Integer>> rules;
	
	public AssociationRules(List<Itemset> items) {
		rules = new HashMap<Set<Integer>, Set<Integer>>();
		if(items.isEmpty())
			throw new EmptyItemSetException("Frequent Itemset is empty!");
		freqItems = items;
	}
	public void generate(int confidence){
		rules.clear();
		
		Itemset itemset = freqItems.get(freqItems.size()-1);
		List<Set<Integer>> sets = itemset.getSets();
		Iterator<Set<Integer>> it = sets.iterator();
		while(it.hasNext()) {
			Set<Integer> I = it.next();
			//generate all subsets
			Set<Set<Integer>> powerSet = Sets.powerSet(I);
			List<Set<Integer>> setList = new ArrayList<Set<Integer>>(powerSet);
			setList.remove(I);                        // remove self
			setList.remove(new HashSet<Integer>());   // remove null
			Iterator<Set<Integer>> s = setList.iterator();
			
			while(s.hasNext()) {
				Set<Integer> tmp = s.next();
				System.out.println(freqItems.get(tmp.size() - 1).getFrequency(tmp));
				//calculate confidence for each nonempty set
				//how to find the support count value in freqItems.get(j);			
			}
		}
	}
}
