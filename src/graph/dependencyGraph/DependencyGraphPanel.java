package graph.dependencyGraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
 
public class DependencyGraphPanel extends JPanel {
	static final long serialVersionUID = 1l;

	JPanel graphPanel = new JPanel(new BorderLayout());
	 
	
	public DependencyGraphPanel() {
		super(new BorderLayout());

		graphPanel.setPreferredSize(new Dimension(700, 560));
		add(graphPanel, BorderLayout.CENTER);
				
		showGraph(DependencyGraph.sample1());
	}
	
	public void showGraph(DependencyGraph dependencyGraph) {		
		Layout<DVertex, DEdge> layout = new ISOMLayout<DVertex, DEdge>(dependencyGraph);
		layout.setSize(new Dimension(600, 400)); 
		VisualizationViewer<DVertex, DEdge> vv =
				new VisualizationViewer<DVertex, DEdge>(layout);
		vv.setPreferredSize(new Dimension(620, 420)); //Sets the viewing area size
		
		Transformer<DVertex,Paint> vertexPaint = new Transformer<DVertex, Paint>() {
			public Paint transform(DVertex v) {
				return v.getPaint();
			}
		}; 
		Transformer<DEdge, Stroke> edgeStrokeTransformer = new Transformer<DEdge, Stroke>() {
			public Stroke transform(DEdge e) {
				return e.getStroke();
			}
		};
		
		Transformer<DEdge, String> edgeLabelTransformer = new Transformer<DEdge, String>() {
			public String transform(DEdge e) {
				return e.getLabel();
			}
		};
		
		Transformer<DVertex, String> vertexLabelTransformer = new Transformer<DVertex, String>() {
			public String transform(DVertex e) {
				return e.getLabel();
			}
		};
		
		Transformer<Context<Graph<DVertex, DEdge>, DEdge>, Shape> edgeShapeTransformer = 
				new Transformer<Context<Graph<DVertex, DEdge>, DEdge>, Shape>() {
			public Shape transform(Context<Graph<DVertex, DEdge>, DEdge> context) {
				return new EdgeShape.QuadCurve<DVertex, DEdge>().transform(context);
			}
		};
		
		Transformer<DVertex, Shape> vertexShapeTransformer = new Transformer<DVertex, Shape>() {
			public Shape transform(DVertex vertex) {
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
				
		DefaultModalGraphMouse<DVertex, DEdge> gm = new DefaultModalGraphMouse<DVertex, DEdge>();
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



 
 
