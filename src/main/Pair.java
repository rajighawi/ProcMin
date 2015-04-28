package main;

import java.util.ArrayList;

public class Pair  implements Comparable<Pair>{
	String s;
	
	public Pair(String s_) {
		super();
		this.s = s_;
	}
	
	public Pair(Character x, Character y) {
		super();
		this.s = x+""+y;
	}
	
	public String getString(){
		return s;
	}
	
	public String toString(){
		return s.charAt(0) + ">" + s.charAt(1);
	}
	
	public String toString2(){
		return s.charAt(0) + " --> " + s.charAt(1);
	}
	
	public Pair reverse(){
		return new Pair(s.charAt(1) + ""+ s.charAt(0));
	}
	
	public Character from(){
		return s.charAt(0);
	}
	
	public Character to(){
		return s.charAt(1);
	}

	@Override
	public int compareTo(Pair arg0) {
		return s.compareTo(arg0.s);
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Pair)) 
			return false;
		Pair q = (Pair) obj;
		return this.from().equals(q.from()) && this.to().equals(q.to());
	}

	public static ArrayList<Pair> distinct(ArrayList<Pair> orderedList){
		ArrayList<Pair> list = new ArrayList<Pair>();
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
	
}
