package graph.petriNet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import petri_nets.Flow;
import petri_nets.Node;
import petri_nets.PetriNet;
import petri_nets.Place;
import petri_nets.Transition;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
 
public class PetriNetGraphMaker  { 
	 
	Map<Node, NodeVertex> nodeMap;
	Graph<NodeVertex, FlowEdge> graph;
	
	public PetriNetGraphMaker() {
		super();
	}
	
	public static Graph<NodeVertex, FlowEdge> makeGraph(PetriNet petriNet) {
		PetriNetGraphMaker graphMaker = new PetriNetGraphMaker();
		graphMaker.go(petriNet);
		return graphMaker.getGraph();
	}
	
	public void go(PetriNet petriNet) {		 
		graph = new DirectedSparseMultigraph<NodeVertex, FlowEdge>();
		nodeMap = new HashMap<Node, NodeVertex>();
		 
		ArrayList<Place> states = petriNet.getPlaces();		
		for (int i = 0; i < states.size(); i++) {
			Place place = states.get(i);			 
			addVertex(place);				 
		} 		
		ArrayList<Transition> transitions = petriNet.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			Transition tr = transitions.get(i);
			addVertex(tr);
		}
		
		ArrayList<Flow> flows = petriNet.getFlows();
		for (int i = 0; i < flows.size(); i++) {
			Flow f = flows.get(i);
			Node from = f.getFrom();
			Node to   = f.getTo();
			NodeVertex vFrom = nodeMap.get(from);
			NodeVertex vTo   = nodeMap.get(to);
			FlowEdge myEdge = new FlowEdge(vFrom, vTo);			
			graph.addEdge(myEdge, vFrom, vTo);
		}
	}
	
	public NodeVertex addVertex(Place place){
		if(nodeMap.containsKey(place)){
			return nodeMap.get(place);
		}		
		NodeVertex v = new NodeVertex(place);		
		graph.addVertex(v);	
		nodeMap.put(place, v);
		return v;
	}
	
	public NodeVertex addVertex(Transition trans){
		if(nodeMap.containsKey(trans)){
			return nodeMap.get(trans);
		}		
		NodeVertex v = new NodeVertex(trans);		
		graph.addVertex(v);	
		nodeMap.put(trans, v);
		return v;
	}

	public Graph<NodeVertex, FlowEdge> getGraph() {
		return graph;
	}
	
}



 
 
