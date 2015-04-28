package graph.transSys;

import java.awt.BasicStroke;
import java.awt.Stroke;
 

public class TransitionEdge {
	
	private String label; // activity label
	private StateVertex source;
	private StateVertex target;
	
	public static final float dash[] = {10.0f};
	public static final Stroke basicStroke = new BasicStroke(1.5f);
	public static final Stroke dashStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
			BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	
	public TransitionEdge(String name, StateVertex source, StateVertex target) {
        this.label = name;
        this.source = source;
        this.target = target;
    }
	
	public StateVertex getSource() {
		return source;
	}

	public StateVertex getTarget() {
		return target;
	}

	public String toString() {
        return label+"["+getSource()+" -> "+getTarget()+"]";
    }
    
    public String getLabel() {
        return label;
    }

    public Stroke getStroke(){
    	return basicStroke;
    }
    
}
