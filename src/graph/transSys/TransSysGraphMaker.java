package graph.transSys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import transition_system.Activity;
import transition_system.State;
import transition_system.Transition;
import transition_system.TransitionSystem;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
 
public class TransSysGraphMaker  { 
	 
	Map<State, StateVertex> stateMap;
	Graph<StateVertex, TransitionEdge> graph;
	
	public TransSysGraphMaker() {
		super();
	}
	
	public static Graph<StateVertex, TransitionEdge> makeGraph(TransitionSystem transitionSystem) {
		TransSysGraphMaker graphMaker = new TransSysGraphMaker();
		graphMaker.go(transitionSystem);
		return graphMaker.getGraph();
	}
	
	public void go(TransitionSystem transitionSystem) {		 
		graph = new DirectedSparseMultigraph<StateVertex, TransitionEdge>();
		stateMap = new HashMap<State, StateVertex>();
		 
		ArrayList<State> states = transitionSystem.getStates();		
		for (int i = 0; i < states.size(); i++) {
			State state = states.get(i);			 
			addVertex(state);				 
		} 
		
		ArrayList<Transition> transitions = transitionSystem.getTransitions();
		for (int i = 0; i < transitions.size(); i++) {
			Transition tr = transitions.get(i);
			State from = tr.getStateFrom();
			Activity activity = tr.getActivity();
			State to = tr.getStateTo();
			StateVertex vFrom = stateMap.get(from);
			StateVertex vTo   = stateMap.get(to);
			TransitionEdge myEdge = new TransitionEdge(activity.getLabel(), vFrom, vTo);			
			graph.addEdge(myEdge, vFrom, vTo);
		}
	}
	
	public StateVertex addVertex(State state){
		if(stateMap.containsKey(state)){
			return stateMap.get(state);
		}			
		String label = state.getLabel();
		byte kind = state.getKind();		
		StateVertex v = new StateVertex(kind, label);		
		graph.addVertex(v);	
		stateMap.put(state, v);
		return v;
	}

	public Graph<StateVertex, TransitionEdge> getGraph() {
		return graph;
	}
	
}



 
 
