package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ActivitySet extends HashSet<Character> implements Comparable<ActivitySet> {
	private static final long serialVersionUID = 1L;	

	public ActivitySet(){
		super();	
	}		

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ActivitySet)){
			return false;
		}
		ActivitySet other = (ActivitySet) obj;
		ArrayList<Character> al1 = new ArrayList<Character>(this);
		ArrayList<Character> al2 = new ArrayList<Character>(other);
		Collections.sort(al1);
		Collections.sort(al2);
		return al1.toString().equals(al2.toString());
	}
	
	@Override
	public String toString(){
		ArrayList<Character> l = new ArrayList<Character>(this);
		Collections.sort(l);		
		return l.toString();
	}	
	
	public static void main(String[] args){
		ActivitySet s1 = new ActivitySet();
		s1.add('a');
		ActivitySet s2 = new ActivitySet();
		s2.add('b');
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1.equals(s2));
		
		SetAssoc sa1 = new SetAssoc(s1, s2);
		System.out.println(sa1);
		
		ActivitySet s3 = new ActivitySet();
		s3.add('a');
		
		ActivitySet s4 = new ActivitySet();
		s4.add('b');
		SetAssoc sa2 = new SetAssoc(s3, s4);
		System.out.println(sa2);
		
		System.out.println(sa1.equals(sa2));
		
		Set<SetAssoc> ss = new HashSet<SetAssoc>();
		ss.add(sa1);
		if(!ss.contains(sa2)){
			ss.add(sa2);
		}
		System.out.println("-------------");
		for (SetAssoc sa: ss) {
			System.out.println(sa);
		}
	}

	@Override
	public int compareTo(ActivitySet o) {
		return this.toString().compareTo(o.toString());
	}
	
	// -------------------------------------------------------------
}
