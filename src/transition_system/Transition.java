package transition_system;

public class Transition {

	State stateFrom;
	Activity activity;
	State stateTo;
	
	public Transition(State stateFrom, Activity activity, State stateTo) {
		super();
		this.stateFrom = stateFrom;
		this.activity = activity;
		this.stateTo = stateTo;
	}

	public State getStateFrom() {
		return stateFrom;
	}

	public Activity getActivity() {
		return activity;
	}

	public State getStateTo() {
		return stateTo;
	}

	@Override
	public String toString() {
		return String.format("%-8s --> %-20s --> %-8s", stateFrom, activity, stateTo);
	}
	
	
	
}
