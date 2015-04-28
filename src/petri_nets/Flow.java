package petri_nets;

public class Flow {

	Node from;
	Node to;
	/*
	public Flow(Node from, Node to) throws Exception {
		super();
		if(from.isPlace()&&to.isPlace()){
			throw new Exception("Flow can't occur in-between places!");
		} else if(from.isTransition()&&to.isTransition()){
			throw new Exception("Flow can't occur in-between transitions!");
		}
		this.from = from;
		this.to = to;		
	}*/
	
	public Flow(Place from, Transition to){
		this.from = from;
		this.to = to;
	}
	
	public Flow(Transition from, Place to){
		this.from = from;
		this.to = to;
	}
	
	public Node getFrom() {
		return from;
	}
	
	public Node getTo() {
		return to;
	}

	@Override
	public String toString() {
		return String.format("%12s --> %-12s", from, to);
	}	
	
}
