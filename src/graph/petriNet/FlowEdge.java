package graph.petriNet;

import java.awt.BasicStroke;
import java.awt.Stroke;
 

public class FlowEdge {
	 
	private NodeVertex source;
	private NodeVertex target;
	
	public static final float dash[] = {10.0f};
	public static final Stroke basicStroke = new BasicStroke(1.5f);
	public static final Stroke dashStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
			BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	
	public FlowEdge(NodeVertex source, NodeVertex target) {
        this.source = source;
        this.target = target;
    }
	
	public NodeVertex getSource() {
		return source;
	}

	public NodeVertex getTarget() {
		return target;
	}

	public String toString() {
        return "["+getSource()+" -> "+getTarget()+"]";
    }
    
    public Stroke getStroke(){
    	return basicStroke;
    }
    
}
