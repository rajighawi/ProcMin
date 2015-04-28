package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnalyzerUtility {

	public static final String _to = "->";
	public static final String _from = "<-";
	public static final String _prll = "||";
	public static final String _choc = " #";
	
	public static Set<Character> list2Set(ArrayList<Character> list){
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < list.size(); i++) {
			set.add(list.get(i));
		}
		return set;
	}
	
	public static ArrayList<String> distinct(ArrayList<String> orderedList){
		ArrayList<String> list = new ArrayList<String>();
		if (orderedList.size() > 0) {
			list.add(orderedList.get(0));
			for (int i = 1; i < orderedList.size(); i++) {
				if (!orderedList.get(i).equals(orderedList.get(i-1))) {
					list.add(orderedList.get(i));
				}
			}
		}
		return list;
	}
	
	public static ArrayList<Pair> slice(String trace){
		ArrayList<Pair> pp = new ArrayList<Pair>();
		for (int i = 0; i < trace.length()-1; i++) {
			String s_ = trace.substring(i, i+2);
			Pair p = new Pair(s_);
			pp.add(p);
		}
		return pp;
	}
	
	/*
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	    	sets.add(new HashSet<T>());
	    	return sets;
	    }
	    List<T> list = new ArrayList<T>(originalSet);
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    for (Set<T> set : powerSet(rest)) {
	    	Set<T> newSet = new HashSet<T>();
	    	newSet.add(head);
	    	newSet.addAll(set);
	    	sets.add(newSet);
	    	sets.add(set);
	    }	    
	    return sets;
	}*/
	
	public static Set<ActivitySet> powerSet(Set<Character> originalSet) {
	    Set<ActivitySet> sets = new HashSet<ActivitySet>();
	    if (originalSet.isEmpty()) {
	    	sets.add(new ActivitySet());
	    	return sets;
	    }
	    List<Character> list = new ArrayList<Character>(originalSet);
	    Character head = list.get(0);
	    Set<Character> rest = new HashSet<Character>(list.subList(1, list.size())); 
	    for (ActivitySet set : powerSet(rest)) {
	    	ActivitySet newSet = new ActivitySet();
	    	newSet.add(head);
	    	newSet.addAll(set);
	    	sets.add(newSet);
	    	sets.add(set);
	    }	    
	    return sets;
	}
	
}
