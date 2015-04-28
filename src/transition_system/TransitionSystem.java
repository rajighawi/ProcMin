package transition_system;

import java.util.ArrayList;

public class TransitionSystem {

	ArrayList<State> states;
	ArrayList<State> initStates;
	ArrayList<State> finalStates;
	ArrayList<Activity> activities;
	ArrayList<Transition> transitions;
	
	public TransitionSystem() {
		this.states      = new ArrayList<State>();
		this.initStates  = new ArrayList<State>();
		this.finalStates = new ArrayList<State>();
		this.activities  = new ArrayList<Activity>();
		this.transitions = new ArrayList<Transition>();
	}
	
	public void addState(State state){
		states.add(state);
		if(state.isInit()){
			initStates.add(state);
		} else if(state.isFinal()){
			finalStates.add(state);
		}
	}
		
	public void addActivity(Activity activity){
		activities.add(activity);
	}
	
	public void addTransition(Transition transition){
		transitions.add(transition);
	}
	
	public void addTransition(State from, Activity activity, State to){
		transitions.add(new Transition(from, activity, to));
	}

	@Override
	public String toString() {
		String s = "TransitionSystem [\n"
				+ "\tInit. States = " + initStates  + "; \n" 
				+ "\tIntr. States = " + states      + "; \n"				
				+ "\tFinal States = " + finalStates + "; \n" 
				+ "\tActivities   = " + activities  + "; \n"
				+ "\tTransitions  = [\n";
		
		for (int i = 0; i < transitions.size(); i++) {
			s += "\t\t"+transitions.get(i)+"\n";
		}
		s += "\t]\n";
		s += "]";
		return s;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public ArrayList<State> getInitStates() {
		return initStates;
	}

	public ArrayList<State> getFinalStates() {
		return finalStates;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	// ---------------------------------------------------------------------
	
	public static TransitionSystem sample1() {
		TransitionSystem ts = new TransitionSystem();
		State s1 = State.initState("s1");
		State s2 = new State("s2");
		State s3 = new State("s3");
		State s4 = new State("s4");
		State s5 = new State("s5");
		State s6 = new State("s6");
		State s7 = State.FinalState("s7");
		
		ts.addState(s1);
		ts.addState(s2);
		ts.addState(s3);
		ts.addState(s4);
		ts.addState(s5);
		ts.addState(s6);
		ts.addState(s7);
		
		Activity  reg_req = new Activity("register request");
		Activity  exa_thr = new Activity("examine thoroughly");
		Activity  exa_cas = new Activity("examine casually"); 
		Activity  chk_tck = new Activity("check ticket");
		Activity  decide  = new Activity("decide");
		Activity  reinit_req = new Activity("reinitiate request");
		Activity  rej_req = new Activity("reject request");
		Activity  pay_cmp = new Activity("pay compensation");
		
		ts.addActivity(reg_req);
		ts.addActivity(exa_thr);
		ts.addActivity(exa_cas);
		ts.addActivity(chk_tck);
		ts.addActivity(decide);
		ts.addActivity(reinit_req);
		ts.addActivity(rej_req);
		ts.addActivity(pay_cmp);
		
		ts.addTransition(s1, reg_req, s2);
		ts.addTransition(s2, exa_cas, s3); 
		ts.addTransition(s2, exa_thr, s3);
		ts.addTransition(s2, chk_tck, s4);
		ts.addTransition(s3, chk_tck, s5);
		ts.addTransition(s4, exa_cas, s5);
		ts.addTransition(s4, exa_thr, s5);
		ts.addTransition(s5, decide, s6);
		ts.addTransition(s6, reinit_req, s2);
		ts.addTransition(s6, pay_cmp, s7);
		ts.addTransition(s6, rej_req, s7);
		
		return ts;
	}
	
}
