package graph.transSys;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import transition_system.State;
import edu.uci.ics.jung.visualization.util.VertexShapeFactory;

public class StateVertex {
	
	byte   kind;	
	String label;
	
	public StateVertex(byte kind, String label) {
		super();
		this.kind  = kind;
		this.label = label;
	}

	public byte getKind() {
		return kind;
	}

	public String getLabel() {
		return label;
	}

	public Paint getPaint(){
		//	return color;
		switch (kind) {
		case State._init:
			return new Color(255,153,153);
		case State._inter:
			return new Color(153,153,255);
		case State._final:
			return new Color(153,255,102);
		default:
			return Color.RED;
		}		
	}

	public Shape getShape(){
		Transformer<StateVertex, Integer> vsv = new Transformer<StateVertex, Integer>() {
			public Integer transform(StateVertex vertex) {
				return 30;
			}
		};		
		Transformer<StateVertex, Float> vsr = new Transformer<StateVertex, Float>() {
			public Float transform(StateVertex vertex) {
				return 1.0f;
			}
		};		
		VertexShapeFactory<StateVertex> vsf = new VertexShapeFactory<StateVertex>(vsv, vsr);
		switch (kind) {
		case State._init:
			return vsf.getRoundRectangle(this);
		case State._inter:
			return vsf.getRectangle(this);
		case State._final:
			return vsf.getEllipse(this);
		default:
			return vsf.getRegularStar(this, 5);
		}			
	}

	public int getSize() {
		return 30;
	}

	@Override
	public String toString() {
		return " <<" + label + ">>";
	}

	
	
}