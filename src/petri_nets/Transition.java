package petri_nets;

public class Transition extends Node{

	public Transition(String label){
		this.label = label;
		this.kind  = _transition;
	}

	@Override
	public String toString() {
		return "[" + label + "]";
	}
	

}
