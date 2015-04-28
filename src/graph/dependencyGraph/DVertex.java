package graph.dependencyGraph;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.visualization.util.VertexShapeFactory;

public class DVertex {
	
	String label;
	
	public DVertex(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Paint getPaint(){
		return new Color(130, 220, 150);				
	}

	public Shape getShape(){
		Transformer<DVertex, Integer> vsv = new Transformer<DVertex, Integer>() {
			public Integer transform(DVertex vertex) {
				return 30;
			}
		};		
		Transformer<DVertex, Float> vsr = new Transformer<DVertex, Float>() {
			public Float transform(DVertex vertex) {
				return 1.0f;
			}
		};		
		VertexShapeFactory<DVertex> vsf = new VertexShapeFactory<DVertex>(vsv, vsr);
		 
		return vsf.getRoundRectangle(this);					
	}

	public int getSize() {
		return 30;
	}

	@Override
	public String toString() {
		return " <<" + label + ">>";
	}

	
	
}