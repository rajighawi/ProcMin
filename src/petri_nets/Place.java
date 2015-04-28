package petri_nets;

public class Place extends Node{

	public Place(String label){
		this.label = label;
		this.kind  = _place;
	}

	@Override
	public String toString() {
		return "(" + label + ")";
	}
	
}
