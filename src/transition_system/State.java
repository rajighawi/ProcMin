package transition_system;

public class State {

	public final static byte _init  = 1;
	public final static byte _inter = 2;
	public final static byte _final = 3;
	
	String label;
	byte   kind;

	public State(String label, byte kind) {
		super();
		this.label = label;
		this.kind  = kind;
	}
	
	public static State initState(String label){
		return new State(label, _init);
	}
	
	public static State FinalState(String label){
		return new State(label, _final);
	}
	
	public State(String label) {
		super();
		this.label = label;
		this.kind  = _inter;
	}

	public String getLabel() {
		return label;
	}
	
	public byte getKind() {
		return kind;
	}
	
	public boolean isInit(){
		return kind == _init;
	}
	
	public boolean isInter(){
		return kind == _inter;
	}
	
	public boolean isFinal(){
		return kind == _final;
	}

	@Override
	public String toString() {
		return "{" + label + "}";
	}
	
}
