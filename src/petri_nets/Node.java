package petri_nets;

public class Node {

	public static final byte _place      = 1;
	public static final byte _transition = 2;
	
	String label;
	byte kind;

	public String getLabel() {
		return label;
	}
	
	public boolean isPlace(){
		return kind == _place;
	}
	
	public boolean isTransition(){
		return kind == _transition;
	}
	
}
