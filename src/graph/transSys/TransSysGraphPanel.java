package graph.transSys;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.collections15.Transformer;

import transition_system.TransitionSystem;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.renderers.CenterEdgeArrowRenderingSupport;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
 
public class TransSysGraphPanel extends JPanel {
	static final long serialVersionUID = 1l;

	JPanel graphPanel = new JPanel(new BorderLayout());
	 
	
	public TransSysGraphPanel() {
		super(new BorderLayout());

		graphPanel.setPreferredSize(new Dimension(700, 560));
		add(graphPanel, BorderLayout.CENTER);
			
		go(TransitionSystem.sample1());		
	}

	
	public void go(TransitionSystem transitionSystem) {
		Graph<StateVertex, TransitionEdge> graph = TransSysGraphMaker.makeGraph(transitionSystem);
		showGraph(graph);
	}
	
	public void showGraph(Graph<StateVertex, TransitionEdge> graph) {		
		//Layout<StateVertex, TransitionEdge> layout = new ISOMLayout<StateVertex, TransitionEdge>(graph);
		Layout<StateVertex, TransitionEdge> layout = new FRLayout<StateVertex, TransitionEdge>(graph);
		
		layout.setSize(new Dimension(600, 400)); 
		VisualizationViewer<StateVertex, TransitionEdge> vv =
				new VisualizationViewer<StateVertex, TransitionEdge>(layout);
		vv.setPreferredSize(new Dimension(620, 420)); //Sets the viewing area size
		
		Transformer<StateVertex,Paint> vertexPaint = new Transformer<StateVertex, Paint>() {
			public Paint transform(StateVertex v) {
				return v.getPaint();
			}
		}; 
		Transformer<TransitionEdge, Stroke> edgeStrokeTransformer = new Transformer<TransitionEdge, Stroke>() {
			public Stroke transform(TransitionEdge e) {
				return e.getStroke();
			}
		};
		
		Transformer<TransitionEdge, String> edgeLabelTransformer = new Transformer<TransitionEdge, String>() {
			public String transform(TransitionEdge e) {
				return e.getLabel();
			}
		};
		
		Transformer<StateVertex, String> vertexLabelTransformer = new Transformer<StateVertex, String>() {
			public String transform(StateVertex e) {
				return e.getLabel();
			}
		};
		
		/*
		Transformer<Context<Graph<StateVertex, TransitionEdge>, TransitionEdge>, Shape> edgeShapeTransformer = 
				new Transformer<Context<Graph<StateVertex, TransitionEdge>, TransitionEdge>, Shape>() {
			public Shape transform(Context<Graph<StateVertex, TransitionEdge>, TransitionEdge> context) {
				return new EdgeShape.QuadCurve<StateVertex, TransitionEdge>().transform(context);
			}
		};*/
		
		Transformer<StateVertex, Shape> vertexShapeTransformer = new Transformer<StateVertex, Shape>() {
			public Shape transform(StateVertex vertex) {
				return vertex.getShape();
			}
		};
		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
		vv.getRenderContext().setVertexShapeTransformer(vertexShapeTransformer);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
	//	vv.getRenderContext().setEdgeShapeTransformer(edgeShapeTransformer);
		
		Renderer.Edge<StateVertex, TransitionEdge> edgeRenderer = vv.getRenderer().getEdgeRenderer();
		edgeRenderer.setEdgeArrowRenderingSupport(new CenterEdgeArrowRenderingSupport<StateVertex, TransitionEdge>());
		//edgeRenderer.setEdgeArrowRenderingSupport(new BasicEdgeArrowRenderingSupport<StateVertex, TransitionEdge>());
		
		DefaultModalGraphMouse<StateVertex, TransitionEdge> gm = new DefaultModalGraphMouse<StateVertex, TransitionEdge>();
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



 
 
