package graph.petriNet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.collections15.Transformer;

import petri_nets.PetriNet;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
 
public class PetriNetGraphPanel extends JPanel {
	static final long serialVersionUID = 1l;

	JPanel graphPanel = new JPanel(new BorderLayout());
	 
	
	public PetriNetGraphPanel() {
		super(new BorderLayout());

		graphPanel.setPreferredSize(new Dimension(700, 560));
		add(graphPanel, BorderLayout.CENTER);
			
		go(PetriNet.sample1());		
	}

	
	public void go(PetriNet petriNet) {
		Graph<NodeVertex, FlowEdge> graph = PetriNetGraphMaker.makeGraph(petriNet);
		showGraph(graph);
	}
	
	public void showGraph(Graph<NodeVertex, FlowEdge> graph) {		
		Layout<NodeVertex, FlowEdge> layout = new ISOMLayout<NodeVertex, FlowEdge>(graph);
		layout.setSize(new Dimension(600, 400)); 
		VisualizationViewer<NodeVertex, FlowEdge> vv =
				new VisualizationViewer<NodeVertex, FlowEdge>(layout);
		vv.setPreferredSize(new Dimension(620, 420)); //Sets the viewing area size
		
		Transformer<NodeVertex,Paint> vertexPaint = new Transformer<NodeVertex, Paint>() {
			public Paint transform(NodeVertex v) {
				return v.getPaint();
			}
		}; 
		Transformer<FlowEdge, Stroke> edgeStrokeTransformer = new Transformer<FlowEdge, Stroke>() {
			public Stroke transform(FlowEdge e) {
				return e.getStroke();
			}
		};
		
		Transformer<FlowEdge, String> edgeLabelTransformer = new Transformer<FlowEdge, String>() {
			public String transform(FlowEdge e) {
				return "";// e.getLabel();
			}
		};
		
		Transformer<NodeVertex, String> vertexLabelTransformer = new Transformer<NodeVertex, String>() {
			public String transform(NodeVertex e) {
				return e.getLabel();
			}
		};
		
		Transformer<Context<Graph<NodeVertex, FlowEdge>, FlowEdge>, Shape> edgeShapeTransformer = 
				new Transformer<Context<Graph<NodeVertex, FlowEdge>, FlowEdge>, Shape>() {
			public Shape transform(Context<Graph<NodeVertex, FlowEdge>, FlowEdge> context) {
				return new EdgeShape.Line<NodeVertex, FlowEdge>().transform(context);
			}
		};
		
		Transformer<NodeVertex, Shape> vertexShapeTransformer = new Transformer<NodeVertex, Shape>() {
			public Shape transform(NodeVertex vertex) {
				return vertex.getShape();
			}
		};
		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
		vv.getRenderContext().setVertexShapeTransformer(vertexShapeTransformer);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
		vv.getRenderContext().setEdgeShapeTransformer(edgeShapeTransformer);
		
		DefaultModalGraphMouse<NodeVertex, FlowEdge> gm = new DefaultModalGraphMouse<NodeVertex, FlowEdge>();
		//	gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);
		vv.addKeyListener(gm.getModeKeyListener());
		
		JScrollPane sp = new JScrollPane(vv);
		sp.setPreferredSize(new Dimension(800, 600));
		graphPanel.removeAll();
		graphPanel.add(sp);
		graphPanel.revalidate();	
	}
	
}



 
 
