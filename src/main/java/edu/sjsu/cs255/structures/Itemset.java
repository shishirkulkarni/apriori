package edu.sjsu.cs255.structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Itemset {
	private Map<Integer, Integer> items;
	
	public Itemset() {
		this.items = new HashMap<Integer, Integer>();
	}
	
	public void loadItemsFromData(Dataset d) {
		items.clear(); // Clear any existing itemset
		
		Iterator<Integer> it = d.data.keySet().iterator();
		
		while(it.hasNext()) {
			Iterator<Integer> tmp = d.data.get(it.next()).iterator();
			while(tmp.hasNext()) {
				items.put(tmp.next(), 0);
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		Iterator<Integer> it = items.keySet().iterator();
		while(it.hasNext()) {
			int key = it.next();
			s.append(key + ": ");
			s.append(items.get(key) + "\n");			
		}
		return s.toString();
	}
	
	public void clear() {
		items.clear();
	}
	
	
}
