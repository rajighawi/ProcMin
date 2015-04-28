package transition_system;

public class Test1 {

	public static void main(String[] args) {
		/*
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
		
		System.out.println(ts);
		*/
		System.out.println("\u8594");
	}

}
