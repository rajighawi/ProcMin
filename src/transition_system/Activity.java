package transition_system;

public class Activity {

	String label;

	public Activity(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return "<" + label + ">";
	}
	
}
