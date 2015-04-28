package main;


public class SetAssoc implements Comparable<SetAssoc>{
	ActivitySet A;
	ActivitySet B;
	
	public SetAssoc(ActivitySet a, ActivitySet b) {
		this.A = a;
		this.B = b;
	}

	public ActivitySet getA() {
		return A;
	}
	
	public ActivitySet getB() {
		return B;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SetAssoc)){
			return false;
		}
		SetAssoc other = (SetAssoc) obj;		
		return (A.equals(other.A) && B.equals(other.B));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((A == null) ? 0 : A.hashCode());
		result = prime * result + ((B == null) ? 0 : B.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return A.toString() + " ==> " + B.toString();
	}
	
	public boolean includes(SetAssoc other){
		return A.containsAll(other.A) && B.containsAll(other.B);
	}

	@Override
	public int compareTo(SetAssoc o) {
		int n = A.compareTo(o.A);
		if(n==0)
			return B.compareTo(o.B);
		else return n;
	}		
	
}
