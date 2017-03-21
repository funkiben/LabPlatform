package test;

import lab.LabFrame;
import lab.component.data.GraphDataSet;
import lab.component.swing.input.DoubleSlider;
import lab.util.HorizontalGraduation;
import lab.util.VerticalGraduation;
import lab.component.data.Graph;

public class GraphTestLab extends LabFrame {
	
	private static final long serialVersionUID = 6299944616449039212L;

	public static void main(String[] args) {
		new GraphTestLab();

	}
	
	private final Graph graph;
	private final GraphDataSet set;
	private final DoubleSlider doubleSlider;
	private double i = 0;
	private double p = 0;
	
	public GraphTestLab() {
		super("Graph Test Lab", 800, 800);
		
		VerticalGraduation vg = new VerticalGraduation(0, 100, 5, 1);
		HorizontalGraduation hg = new HorizontalGraduation(0, 20, 5, 1);
		
		
		graph = new Graph(750, 500, "TEST GRAPH", "", "", hg, vg);
		
		addComponent(graph);
		
		doubleSlider = new DoubleSlider(750, 0, 100, 1, DoubleSlider.HORIZONTAL);
		
		set = new GraphDataSet("test", true, false);
		
		graph.addDataSet(set);
		
		doubleSlider.setOffsetY(50);
		
		
		addComponent(doubleSlider);
		
		start(30);
		
	}

	@Override
	public void update() {
		i += 0.05;
		
		p = lerp(p, doubleSlider.getValue() / 100.0 * graph.getvGraduation().getEnd(), 0.1f);
		
		set.addPoint(i, p);
		
		graph.gethGraduation().setStart((int) i - 10);
		graph.gethGraduation().setEnd((int) i + 10);
		
	}
	
	private static double lerp(double v1, double v2, float f) {
		return (v2 - v1) * f + v1;
	}

}
