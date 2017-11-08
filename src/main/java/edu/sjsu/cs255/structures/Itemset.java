package edu.sjsu.cs255.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.sjsu.cs255.common.Options;

public class Itemset {
	private Map<Set<Integer>, Integer> items;
	private Dataset d;
	
	public Itemset() {
		this.items = new HashMap<Set<Integer>, Integer>();
	}
	
	public List<Set<Integer>> getSets(){
		return new ArrayList<Set<Integer>>(items.keySet());
	}
	
	public Itemset(Dataset d) { // initialize data set
		this();
		this.d = d;
	}

	public void loadItemsFromData(int minSupport) {
		items.clear(); // Clear any existing item set
		
		Iterator<Integer> it = d.data.keySet().iterator();
		
		while(it.hasNext()) {        //transactions
			Iterator<Integer> tmp = d.data.get(it.next()).iterator();		
			
			while(tmp.hasNext()) {   // items
				Set<Integer> set = new HashSet<Integer>();
				set.add(tmp.next());
				if (items.get(set) == null)   // check if it is a new item
					items.put(set, 1);
				else
					items.put(set,items.get(set) + 1); // increment item count
			}
		}
		
		filterLowerSupportCountItems(minSupport);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		Iterator<Set<Integer>> it = items.keySet().iterator();
		while(it.hasNext()) {
			Set<Integer> key = it.next();
			s.append(key + ": ");
			s.append(items.get(key) + "\n");			
		}
		return s.toString();
	}
	
	public void clear() {
		items.clear();
	}
	
	public Itemset generateNextFrequentItemsets(int minSupport) {
		Itemset candidateItem = new Itemset(this.d);
		Iterator<Set<Integer>> i = items.keySet().iterator();
		while(i.hasNext()) {
			Iterator<Set<Integer>> j = items.keySet().iterator();
			Set<Integer> item1 = i.next();
			while(j.hasNext()) {
				Set<Integer> item2 = j.next();
				if(item1 == item2)
					continue;
				Set<Integer> union = new HashSet<Integer>(item1);				
				union.addAll(item2);
				//Next item set generated = current item set size + 1
				if (union.size() != item1.size() + 1)
					continue;
				if(getOccurence(union) >= minSupport)
					candidateItem.insert(union, getOccurence(union));
			}
		}
	    return candidateItem;
	}
	
	private void insert(Set<Integer> key, int value) {
		items.put(key, value);
	}
     
	private int getOccurence(Set<Integer> union) {
		int count=0;
		Iterator<Integer> it = d.data.keySet().iterator();
		while(it.hasNext()) {        //transactions
			Set<Integer> tmp = d.data.get(it.next());		
		    if(tmp.containsAll(union))
		    		count++;
		}
		return count;
    }
	
	private void filterLowerSupportCountItems(int minSupport) {
		Iterator<Set<Integer>> i = items.keySet().iterator();
		while(i.hasNext()) {
			Set<Integer> item = i.next();
			if(items.get(item) < minSupport)
				i.remove();
		}
	}
	
	public int size() {
		return items.size();
	}
	
	public void prune(Itemset freqItems) {
		//Get frequent item set
		//Generate combinations of that item set(n) of the size n-1
		//If even any one is absent in Frequent item sets of step 1, don't consider
		Iterator<Set<Integer>> i = items.keySet().iterator();

		while(i.hasNext()) {
			Set<Integer> item1 = i.next();
			if(item1.size() < 3)
        			return;
			int j;
			for(j=0;j<item1.size();j++) {
				ArrayList<Integer> parentSet=new ArrayList<Integer>(item1);	
				parentSet.remove(j);
				Set<Integer> subSet = new HashSet<Integer>(parentSet);
				if(!freqItems.subsetExists(subSet))
					break;
			}
			if(j < item1.size()) {
				items.remove(item1);
			}
		}		
	}
	
	private boolean subsetExists(Set<Integer> set){
		return items.get(set)==null ? false: true;
	}
	
	public int getFrequency(Set<Integer> s) {
		return items.get(s);
	}
}
