package graph.dependencyGraph;

import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

public class DependencyGraph extends DirectedSparseMultigraph<DVertex, DEdge>{
	private static final long serialVersionUID = 1L;
	
	public DependencyGraph(){
		super();
	}
	
	public static DependencyGraph sample1(){
		DependencyGraph graph = new DependencyGraph();
		String s[] = {"a","b","c","d","e","f","g","h","i"};		
		Map<String, DVertex> map = graph.batchAddVertices(s);
		DVertex a = map.get("a");
		DVertex b = map.get("b");
		DVertex c = map.get("c");
		DVertex d = map.get("d");
		DVertex e = map.get("e");
		DVertex f = map.get("f");
		DVertex g = map.get("g");
		DVertex h = map.get("h");
		DVertex i = map.get("i");
		graph.addEdge(""+0.72, a, c);
		graph.addEdge(""+0.64, a, d);
		graph.addEdge(""+0.75, a, b);
		graph.addEdge(""+0.77, c, f);
		graph.addEdge(""+0.68, b, g);
		graph.addEdge(""+0.98, d, f);
		graph.addEdge(""+0.84, f, h);
		graph.addEdge(""+0.83, h, e);
		graph.addEdge(""+0.92, e, f);
		graph.addEdge(""+0.75, f, g);
		graph.addEdge(""+0.66, g, f);
		graph.addEdge(""+0.93, g, i);
		graph.addEdge(""+0.77, h, i);
		return graph;
	}
	
	public void addEdge(String w, DVertex v1, DVertex v2){
		DEdge e = new DEdge(w, v1, v2); 
		addEdge(e, v1, v2);
	}
	
	public Map<String, DVertex> batchAddVertices(String[] s){
		Map<String, DVertex> map = new HashMap<String, DVertex>();
		for (int i = 0; i < s.length; i++) {
			DVertex v = new DVertex(s[i]);
			addVertex(v);
			map.put(s[i], v);
		}
		return map;
	}
	
	/*
	ArrayList<DVertex> verteces;
	ArrayList<DEdge>   edges;
	
	public DependencyGraph(ArrayList<DVertex> verteces, ArrayList<DEdge> edges) {
		super();
		this.verteces = verteces;
		this.edges = edges;
	}
	
	public DependencyGraph() {
		super();
		this.verteces = new ArrayList<DVertex>();
		this.edges = new ArrayList<DEdge>();
	}
	
	public void addDVertex(DVertex vertex){
		verteces.add(vertex);
		addVertex(vertex);
	}
	
	public void addDEdge(DEdge edge){
		edges.add(edge);
		addEdge(edge, edge.getSource(), edge.getTarget());
	}
	
	public void addDEdge(DVertex from, DVertex to, double weight){
		DEdge edge = new DEdge(weight, from, to);
		edges.add(edge);
		addEdge(edge, edge.getSource(), edge.getTarget());
	}
	
	public ArrayList<DVertex> getVerteces() {
		return gverteces;
	}
	
	public ArrayList<DEdge> getEdges() {
		return edges;
	}	
	*/
}
