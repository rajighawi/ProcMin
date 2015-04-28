package graph.petriNet;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;

import org.apache.commons.collections15.Transformer;

import petri_nets.Node;
import petri_nets.Place;
import petri_nets.Transition;
import edu.uci.ics.jung.visualization.util.VertexShapeFactory;

public class NodeVertex {
	
	byte   kind;	// _place vs. _transition
	String label; 
	
	private NodeVertex(byte kind, String label) {
		super();
		this.kind  = kind;
		this.label = label;
	}
	
	public NodeVertex(Place place) {
		this(Node._place, place.getLabel());
	}
	
	public NodeVertex(Transition transition) {
		this(Node._transition, transition.getLabel());
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
		case Node._place:
			return new Color(255,255,170);
		case Node._transition:
			return Color.PINK; //new Color(200,200,230);
		default:
			return Color.RED;
		}		
	}

	public Shape getShape(){
		Transformer<NodeVertex, Integer> vsv = new Transformer<NodeVertex, Integer>() {
			public Integer transform(NodeVertex vertex) {
				return 30;
			}
		};		
		Transformer<NodeVertex, Float> vsr = new Transformer<NodeVertex, Float>() {
			public Float transform(NodeVertex vertex) {
				return 1.0f;	
			}
		};		
		VertexShapeFactory<NodeVertex> vsf = new VertexShapeFactory<NodeVertex>(vsv, vsr);
		switch (kind) {
		case Node._transition:
			return vsf.getRectangle(this);
		case Node._place:
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