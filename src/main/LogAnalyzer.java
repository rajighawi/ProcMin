package main;

import graph.dependencyGraph.DEdge;
import graph.dependencyGraph.DVertex;
import graph.dependencyGraph.DependencyGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import petri_nets.Flow;
import petri_nets.Place;
import petri_nets.Transition;
import petri_nets.WorkflowNet;

import com.google.common.collect.HashBasedTable;

public class LogAnalyzer {
	
	public static final String _to = "->";
	public static final String _from = "<-";
	public static final String _prll = "||";
	public static final String _choc = " #";
	
	ArrayList<Trace> traces;
	ArrayList<Character> activities;
	ActivitySet inputActivities;
	ActivitySet outputactivities;
	ArrayList<Pair> directSuccession;
	ArrayList<Pair> causalities;
	HashBasedTable<Character, Character, String> footprint;
	HashBasedTable<Character, Character, Integer> directSuccessionTable;
	HashBasedTable<Character, Character, Double> dependencyTable;
	Set<SetAssoc> setAssociations;
	Map<Character, ActivitySet> destMap;
	Map<Character, ActivitySet> prvnMap;
	
	public LogAnalyzer(ArrayList<Trace> traces){
		this.traces = traces;
		this.activities       = new ArrayList<Character>();
		this.inputActivities  = new ActivitySet();
		this.outputactivities = new ActivitySet();
		this.directSuccession = new ArrayList<Pair>();
		this.causalities      = new ArrayList<Pair>();
		this.footprint        = HashBasedTable.create();
		directSuccessionTable = HashBasedTable.create();
		this.dependencyTable  = HashBasedTable.create();
		this.setAssociations  = new HashSet<SetAssoc>();
		this.destMap = new HashMap<Character, ActivitySet>();
		this.prvnMap = new HashMap<Character, ActivitySet>();
		
		analyze();
	}
	
	// ----------------------------------------------------------
	public static void main(String[] args) {
		//String[] L = {"abcd","acbd","abcefbcd","abcefcbd","acbefbcd","acbefbcefcbd"};
		String[] L = {"abcd","acbd","aed"};
		ArrayList<Trace> traces = Trace.parse(L);
		LogAnalyzer analyzer = new LogAnalyzer(traces);
		analyzer.analyze();			
		analyzer.printFootPrint();	 

		// --------------- Step 4. -------------------
		System.out.println("-----------------------");
		analyzer.printDest();
		System.out.println("-----------------------");
		analyzer.printProvn();
		System.out.println("-----------------------");
		analyzer.printSetAssociations();

		analyzer.makeWorkflowNet();
		
	}
	// ----------------------------------------------------------
	
	public void analyze(){
		go1();
		findFootprint();
		findDestProvn();
		findSetAssociations();
	}

	public void go1(){
		for (int i = 0; i < traces.size(); i++) {
			Trace trace = traces.get(i);
			String trc = trace.getTrace();
			for (int j = 0; j < trc.length(); j++) {
				Character c = trc.charAt(j);
				if(!activities.contains(c)){
					activities.add(c);
				}
			}			
		}
		Collections.sort(activities);
		Map<String, Integer> dfMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				directSuccessionTable.put(x, y, 0);
				dfMap.put(x+""+y, 0);
			}
		}		
				
		for (int i = 0; i < traces.size(); i++) {
			Trace trace = traces.get(i);
			String trc  = trace.getTrace();
			int freq    = trace.getFreq();
			Character s = trc.charAt(0);
			Character e = trc.charAt(trc.length()-1);
			if(!inputActivities.contains(s)){
				inputActivities.add(s);
			}
			if(!outputactivities.contains(e)){
				outputactivities.add(e);
			}
			
			ArrayList<Pair> slice = AnalyzerUtility.slice(trc);
			for (int j = 0; j < slice.size(); j++) {
				Pair p = slice.get(j);
				if(dfMap.containsKey(p.getString())){
					Integer f_ = dfMap.get(p.getString());
					dfMap.put(p.getString(), f_+freq);		
				} 						 
			}			
			directSuccession.addAll(slice);
		}
		Collections.sort(directSuccession);
		directSuccession = Pair.distinct(directSuccession);
		
		ArrayList<Pair> plist = new ArrayList<Pair>(directSuccession);
		while (!plist.isEmpty()) {
			Pair p = plist.get(0);
			Pair q = p.reverse();
			if(plist.contains(q)){
				plist.remove(q);
			} else {
				causalities.add(p);
			}
			plist.remove(p);
		}
		
		ArrayList<String> pp = new ArrayList<String>();
		pp.addAll(dfMap.keySet());
		for (int i = 0; i < pp.size(); i++) {
			String p = pp.get(i);
			Character x = p.charAt(0);
			Character y = p.charAt(1);
			int value = dfMap.get(p);
			directSuccessionTable.put(x, y, value);
		}
		
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);			
			for (int j = i; j < activities.size(); j++) {
				Character y = activities.get(j);				
				if(x==y){
					int xxV = directSuccessionTable.get(x, x);
					double dV = xxV/(1.+xxV);
					dependencyTable.put(x, x, dV);
				} else {
					int xyV = directSuccessionTable.get(x, y);
					int yxV = directSuccessionTable.get(y, x);
					double den = 1.+xyV+yxV;
					double dV1 = (xyV-yxV)/den;
					dependencyTable.put(x, y, dV1);
					dependencyTable.put(y, x, -dV1);
				}				
			}
		}		
	}
	
	public void findFootprint(){
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			for (int j = i; j < activities.size(); j++) {
				Character y = activities.get(j);
				Pair p = new Pair(x,y);
				Pair q = p.reverse();
				String v1 = "";
				String v2 = "";
				if(directSuccession.contains(p)){ // x>y
					if(directSuccession.contains(q)){ // y>x
						v1 = _prll; v2 = _prll;
					} else { // ! y>x
						v1 = _to; v2 = _from;
					}
				} else { // !x>y 
					if(directSuccession.contains(q)){ // y>x
						v1 = _from; v2 = _to; 
					} else { // ! y>x
						v1 = _choc; v2 = _choc;
					}					
				}				
				footprint.put(x, y, v1);
				footprint.put(y, x, v2);
			}
		}		
	}
	
	public void findDestProvn(){
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			ActivitySet dest = new ActivitySet(); 
			ActivitySet prvn = new ActivitySet(); 
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				String v = footprint.get(x, y);
				if(v.equals(_to)){
					dest.add(y);
				} else if(v.equals(_from)){
					prvn.add(y);
				} 
			}
			if(dest.size()>0){
				destMap.put(x, dest); 
			}
			if(prvn.size()>0){
				prvnMap.put(x, prvn); 
			}
		}
	}
	
	public void findSetAssociations(){
		Set<SetAssoc> assocs1 = new HashSet<SetAssoc>();
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			if(destMap.containsKey(x)){
				ActivitySet from = new ActivitySet();
				from.add(x);
				ActivitySet to = destMap.get(x);
				if(to.size()==1){
					assocs1.add(new SetAssoc(from, to));					
				} else {
					Set<ActivitySet> psTo = AnalyzerUtility.powerSet(to);
					for(ActivitySet s: psTo){
						if(s.size()==0) continue;
						assocs1.add(new SetAssoc(from, s));
					}					
				}				
			}
			if(prvnMap.containsKey(x)){
				ActivitySet to = new ActivitySet();
				to.add(x);
				ActivitySet from = prvnMap.get(x);
				if(from.size()==1){
					assocs1.add(new SetAssoc(from, to));					
				} else {
					Set<ActivitySet> psFrom = AnalyzerUtility.powerSet(from);
					for(ActivitySet s: psFrom){
						if(s.size()==0) continue;
						assocs1.add(new SetAssoc(s, to));
					}
				}
			}
		}
		//-----------------------------------
		Set<SetAssoc> assocs2 = verifySetAssociations(assocs1);
		Set<SetAssoc> assocs3 = maximizeSetAssociations(assocs2);
		/*
		System.out.println("---------- expand ----------");
		for (SetAssoc sa : assocs1) {
			System.out.println("_______\t: "+sa);
		}			
		System.out.println("---------- verify ----------");		
		for (SetAssoc sa : assocs2) {
			System.out.println("_______\t: "+sa);
		}		
		System.out.println("---------- maximize ----------");		
		for (SetAssoc sa : assocs3) {
			System.out.println("_______\t: "+sa);
		}*/
		setAssociations = assocs3;
	}
	
	public Set<SetAssoc> maximizeSetAssociations(Set<SetAssoc> assocs){
		Set<SetAssoc> minimals = new HashSet<SetAssoc>();
		for (SetAssoc sa1 : assocs) {
			for (SetAssoc sa2 : assocs) {
				if(!sa1.equals(sa2)){
					if(sa1.includes(sa2)){
						minimals.add(sa2);
					}
				}
			}
		}
		Set<SetAssoc> maximals = new HashSet<SetAssoc>();
		for (SetAssoc sa : assocs) {
			if(!minimals.contains(sa)){
				maximals.add(sa);
			}
		}		
		return maximals;
	}
	
	public Set<SetAssoc> verifySetAssociations(Set<SetAssoc> assocs){
		Set<SetAssoc> assocs2 = new HashSet<SetAssoc>();
		for (SetAssoc sa : assocs) {
			ActivitySet A = sa.A;
			ActivitySet B = sa.B;
			boolean validA = isAllInterChoice(A);
			boolean validB = isAllInterChoice(B);
			if(validA && validB){
				assocs2.add(sa);
			}
		}
		return assocs2;
	}
	
	// -------------------- Utility functions --------------------------

	
	public void printDest(){
		Iterator<Character> iter = destMap.keySet().iterator();
		while (iter.hasNext()) {
			Character x = iter.next();
			ActivitySet dest = destMap.get(x);
			System.out.println(x+" ===> "+dest);		
		}		 
	}
	
	public void printProvn(){
		Iterator<Character> iter = prvnMap.keySet().iterator();
		while (iter.hasNext()) {
			Character x = iter.next();
			ActivitySet prvn = prvnMap.get(x);
			System.out.println(prvn+" ===> "+x);		
		}		 
	}
	
	public void printSetAssociations(){
		for (SetAssoc assoc : setAssociations) {
			System.out.println(assoc);
		}
	}
	
	public String printCausalities(){
		String s = "";
		for (int i = 0; i < causalities.size(); i++) {
			s += ((i>0?", ":"")+causalities.get(i).toString2());
		}
		return s;
	}
	

	
	
	
	public void printFootPrint(){
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			System.out.print("\t"+x);
		}
		System.out.println();
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			System.out.print(x);
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				String v = footprint.get(x, y);
				System.out.print("\t"+v);
			}
			System.out.println();
		}
	}
	
	//-------------------------------------------------------------
	
	public WorkflowNet makeWorkflowNet(){
		Place start = new Place("start");
		Place end   = new Place("end");
		WorkflowNet workflowNet = new WorkflowNet(start, end);
		 
		Map<Character, Transition> transitionMap = new HashMap<Character, Transition>();
		for (int i = 0; i < activities.size(); i++) {
			Character actv = activities.get(i);
			Transition transition = new Transition(actv+"");
			workflowNet.addTransition(transition);
			transitionMap.put(actv, transition);
		}
		
		for (Character ia : inputActivities) {
			workflowNet.addFlow(start, transitionMap.get(ia));
		}
		for (Character oa : outputactivities) {
			workflowNet.addFlow(transitionMap.get(oa), end);
		}
		
		int i = 1;
		for (SetAssoc setAssoc : setAssociations) {
			ActivitySet A = setAssoc.getA();
			ActivitySet B = setAssoc.getB();
			//String s = "P"+(i+1)+" {"+A.toString()+", "+B.toString()+"}";
			String s = "P"+i;
			Place place = new Place(s);
			workflowNet.addPlace(place);
			
			for (Character a : A) {
				Transition t1 = transitionMap.get(a);
				Flow flow = new Flow(t1, place);
				workflowNet.addFlow(flow);
			}			
			for (Character b : B) {
				Transition t2 = transitionMap.get(b);
				Flow flow = new Flow(place, t2);
				workflowNet.addFlow(flow);
			}
			i++;
		}
		System.out.println("===============================");
		System.out.println(workflowNet);
		return workflowNet;
	}
	
	public boolean isAllInterChoice(ActivitySet A){
		if(A.size()<=1) {
			return true;
		}
		for (Character ai :A) {
			for (Character aj : A) {
				String v = footprint.get(ai, aj);
				if(!v.equals(_choc)){
					return false; 
				}
			}
		}
		return true;
	}
	
	public DependencyGraph makeDependencyGraph(int tauDS, double tauDP){
		DependencyGraph graph = new DependencyGraph();
		Map<String, DVertex> verMap = new HashMap<String, DVertex>();
		for (int i = 0; i < activities.size(); i++) {
			Character x = activities.get(i);
			String x_ = x + "";
			for (int j = 0; j < activities.size(); j++) {
				Character y = activities.get(j);
				String y_ = y + "";
				int ds = directSuccessionTable.get(x, y);
				double dp = dependencyTable.get(x, y);
				if(ds>=tauDS && dp>=tauDP){
					DVertex vX = null;
					DVertex vY = null;
					if(verMap.containsKey(x_)){
						vX = verMap.get(x_);
					} else {
						vX = new DVertex(x_);
						graph.addVertex(vX);
						verMap.put(x_, vX);
					}
					if(verMap.containsKey(y_)){
						vY = verMap.get(y_);
					} else {
						vY = new DVertex(y_);
						graph.addVertex(vY);
						verMap.put(y_, vY);
					}
					String label = String.format("%d (%-7.4f)", ds, dp);
					DEdge e = new DEdge(label, vX, vY);
					graph.addEdge(e, vX, vY);
				}
			}
		}
		return graph;
	}

	//-------------------------------------------------------------
		
	public ArrayList<Trace> getTraces() {
		return traces;
	}

	public ArrayList<Character> getActivities() {
		return activities;
	}

	public ActivitySet getInputActivities() {
		return inputActivities;
	}

	public ActivitySet getOutputactivities() {
		return outputactivities;
	}

	public ArrayList<Pair> getDirectSuccession() {
		return directSuccession;
	}

	public ArrayList<Pair> getCausalities() {
		return causalities;
	}

	public HashBasedTable<Character, Character, String> getFootprint() {
		return footprint;
	}

	public Set<SetAssoc> getSetAssociations() {
		return setAssociations;
	}

	public Map<Character, ActivitySet> getDestMap() {
		return destMap;
	}

	public Map<Character, ActivitySet> getPrvnMap() {
		return prvnMap;
	}

	public HashBasedTable<Character, Character, Integer> getDirectSuccessionTable() {
		return directSuccessionTable;
	}

	public HashBasedTable<Character, Character, Double> getDependencyTable() {
		return dependencyTable;
	}
		
	
}
