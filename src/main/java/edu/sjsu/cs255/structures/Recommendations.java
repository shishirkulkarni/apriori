package edu.sjsu.cs255.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recommendations {
	private Map<Integer, List<Integer>> recommendationMap;
	
	public Recommendations() {
		recommendationMap = new HashMap<Integer, List<Integer>>();
	}
	
	public void generateRecommendations(NetworkGraph g, AssociationRules rules, Dataset d) {
		//TODO: for every node in graph G, filter applicable association rules.
		//Check if all the friends of node also have that rule applicable. If yes, recommend it to him.
		Iterator<Integer> it = g.nodes().iterator();
		while(it.hasNext()) {
			int person = it.next();
			List<Integer> recommendations = new ArrayList<Integer>();
			Set<Integer> shoppingList = d.getShoppingList(person);
			Iterator<Set<Integer>> rulesKeys = rules.keySet().iterator();
			while(rulesKeys.hasNext()) {
				Set<Integer> ruleKey = rulesKeys.next();
				if(!shoppingList.containsAll(ruleKey) || shoppingList.containsAll(rules.getImplications(ruleKey)))
					continue;
				Iterator<Integer> friends = g.getFriends(person).iterator();
				while(friends.hasNext()) {
					int friend = friends.next();
					Set<Integer> friendList = d.getShoppingList(friend);
					Set<Integer> implications = rules.getImplications(ruleKey);
					if(d.getShoppingList(friend).containsAll(ruleKey)) {
						if(d.getShoppingList(friend).containsAll(rules.getImplications(ruleKey)))
							recommendations.addAll(rules.getImplications(ruleKey));
					}
				}
			}
			recommendationMap.put(person, recommendations);
		}
	}
	
	public String toString() {
		return recommendationMap.toString();	
	}
	
	public void printRecommendations(DataCatalog catalog, PersonCatalog personNames) {
		Iterator<Integer> personIterator = this.recommendationMap.keySet().iterator(); 
		while(personIterator.hasNext()) {
			Integer person = personIterator.next();
			System.out.print(personNames.getName(person)+": ");
			Iterator<Integer> ruleIterator = recommendationMap.get(person).iterator();
			while(ruleIterator.hasNext()) {
				Integer rule = ruleIterator.next();
				System.out.print(catalog.getName(rule)+" ");
			}
			System.out.println();
		}
	}
}
