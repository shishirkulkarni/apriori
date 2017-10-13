package edu.sjsu.cs255.structures;

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
	
	public Itemset(Dataset d) { // initialize data set
		this();
		this.d = d;
	}
	public void loadItemsFromData() {
		items.clear(); // Clear any existing itemset
		
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
	
	public Itemset generateFrequentItemsets(int minSupport) {
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
	
/*	private void filterCandidates(int minSupport) {
		Iterator<Set<Integer>> i = items.keySet().iterator();
		while(i.hasNext()) {
			Set<Integer> item = i.next();
			if(items.get(item) < minSupport)
				i.remove();
		}
	}*/
	
	public int size() {
		return items.size();
	}
}
