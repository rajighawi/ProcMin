package graph.dependencyGraph;

import java.awt.BasicStroke;
import java.awt.Stroke;
 

public class DEdge {
	
	private String label; // activity label
	private DVertex source;
	private DVertex target;
	
	public static final float dash[] = {10.0f};
	public static final Stroke basicStroke = new BasicStroke(1.5f);
	public static final Stroke dashStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
			BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	
	public DEdge(String label, DVertex source, DVertex target) {
        this.label = label;
        this.source = source;
        this.target = target;
    }
	
	public DVertex getSource() {
		return source;
	}

	public DVertex getTarget() {
		return target;
	}

	public String toString() {
        return label + " ["+getSource()+" ---> "+getTarget()+"]";
    } 

    public String getLabel() {
		return label;
	}

	public Stroke getStroke(){
    	return basicStroke;
    }
    
}
