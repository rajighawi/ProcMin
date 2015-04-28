package petri_nets;


public class WorkflowNet extends PetriNet{

	Place start;
	Place end;
	
	public WorkflowNet(Place start, Place end) {
		super();
		this.start = start;
		this.end = end;
		addPlace(start);
		addPlace(end);
	}
	 
	@Override
	public String toString() {
		String s = "Workflow Net [\n"
				+ "\tStart Place = " + start      + "; \n"		
				+ "\tEnd Place   = " + end      + "; \n"		
				+ "\tPlaces      = " + places      + "; \n"		
				+ "\tTransitions = " + transitions + "; \n" 
				+ "\tFlows       = [\n";
		for (int i = 0; i < flows.size(); i++) {
			s += "\t\t"+flows.get(i)+"\n";
		}
		s += "\t]\n";
		s += "]";
		return s;
	}
	
	// ---------------------------------------------------------------------
	
	public static WorkflowNet sample1() {
		Place start = new Place("start");
		Place end   = new Place("end");
		WorkflowNet pn = new WorkflowNet(start, end);
		Place c1    = new Place("c1");
		Place c2    = new Place("c2");
		Place c3    = new Place("c3");
		Place c4    = new Place("c4");
		Place c5    = new Place("c5");
		
		pn.addPlace(c1);
		pn.addPlace(c2);
		pn.addPlace(c3);
		pn.addPlace(c4);
		pn.addPlace(c5);

		Transition a = new Transition("a");
		Transition b = new Transition("b");
		Transition c = new Transition("c");
		Transition d = new Transition("d");
		Transition e = new Transition("e");
		Transition f = new Transition("f");
		Transition g = new Transition("g");
		Transition h = new Transition("h");
		pn.addTransition(a);
		pn.addTransition(b);
		pn.addTransition(c);
		pn.addTransition(d);
		pn.addTransition(e);
		pn.addTransition(f);
		pn.addTransition(g);
		pn.addTransition(h);
		pn.addFlow(start, a);	pn.addFlow(a, c1);	pn.addFlow(a, c2); 
		pn.addFlow(c1, b);	pn.addFlow(c1, c);	pn.addFlow(c2, d); 	pn.addFlow(b, c3); 	
		pn.addFlow(c, c3);	pn.addFlow(d, c4);	pn.addFlow(c3, e);	pn.addFlow(c4, e);	
		pn.addFlow(e, c5);	pn.addFlow(c5, f );	pn.addFlow(f, c1);	pn.addFlow(f, c2); 
		pn.addFlow(c5, g);	pn.addFlow(c5,h);	pn.addFlow(g, end);	pn.addFlow(h, end);
		/*
		P = {start, c1, c2, c3,	c4, c5, end}, 
		T = {a, b, c, d, e, f, g,h}, 
 		F = {(start, a), (a, c1), (a, c2), (c1, b),
			 (c1, c), (c2, d), (b, c3), (c, c3), (d, c4), (c3, e),
			 (c4, e), (e, c5), (c5, f ), (f, c1),
			 (f, c2), (c5, g), (c5,h), (g, end), (h, end)}.
		*/
		
		return pn;
	}
}
