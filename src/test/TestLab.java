package test;

import java.awt.Color;

import lab.LabFrame;
import lab.component.container.Beaker;
import lab.component.container.Bulb;
import lab.component.container.ContentState;
import lab.component.container.Flask;
import lab.component.container.GraduatedCylinder;
import lab.component.data.GraphDataSet;
import lab.component.data.Graph;
import lab.component.sensor.Manometer;
import lab.component.sensor.Thermometer;
import lab.component.swing.input.Button;
import lab.component.swing.input.Switch;
import lab.component.swing.input.field.DoubleField;
import lab.component.swing.input.slider.DoubleSlider;
import lab.util.HorizontalGraduation;
import lab.util.VerticalGraduation;

public class TestLab extends LabFrame {

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new TestLab("Test Lab", 800, 800);
	}
	
	private final Graph graph;
	private final Beaker beaker;
	private final Bulb bulbLEGACY;
	private final Flask flask;
	private final GraduatedCylinder graduatedCylinder;
	private final Thermometer thermometer;
	private final Manometer manometer;
	private final DoubleSlider doubleSlider;
	private final Switch switchC;
	private final DoubleField textInput;
	private final Button button;
	private double t = 0;
	
	public TestLab(String name, int width, int height) {
		super(name, width, height);
		
		VerticalGraduation vg = new VerticalGraduation(-20, 20, 5, 1);
		HorizontalGraduation hg = new HorizontalGraduation(-20, 20, 5, 1);
		
		graph = new Graph(200, 200, "Test Graph", "m", "m", hg, vg);
		
		GraphDataSet set = new GraphDataSet("test1", true, false);
		for (double i = -50; i <= 50; i+=0.1) {
			set.addPoint(i, i * i * i * 0.005);
		}
		graph.addDataSet(set);
		
		set = new GraphDataSet("test2", true, false);
		for (double i = -50; i <= 50; i+=0.1) {
			set.addPoint(i, i * i * i * 0.01);
		}
		set.setColor(Color.blue);
		graph.addDataSet(set);
		
		set = new GraphDataSet("test3", true, false);
		for (double i = -50; i <= 50; i+=0.1) {
			set.addPoint(i, i * i * i * 0.05);
		}
		set.setColor(Color.green);
		graph.addDataSet(set);
		
		
		addComponent(graph);
		
		
		beaker = new Beaker(100, 200);
		beaker.setContentColor(Color.blue);
		beaker.setContentState(ContentState.SOLID);
		beaker.setValue(10);
		addComponent(beaker);
		
		flask = new Flask(100, 200);
		flask.setContentColor(Color.blue);
		flask.setContentState(ContentState.LIQUID);
		flask.setValue(10);
		addComponent(flask);
		
		graduatedCylinder = new GraduatedCylinder(30, 200);
		graduatedCylinder.setContentColor(Color.blue);
		graduatedCylinder.setContentState(ContentState.LIQUID);
		graduatedCylinder.setValue(10);
		addComponent(graduatedCylinder);
		
		bulbLEGACY = new Bulb(200, 200);
		bulbLEGACY.setContentState(ContentState.SOLID);
		bulbLEGACY.setContentColor(new Color(240, 240, 240));
		bulbLEGACY.setValue(10);
		bulbLEGACY.setGraduation(new VerticalGraduation(0, 100, 10, 2));
		addComponent(bulbLEGACY);
		
		thermometer = new Thermometer(300);
		thermometer.setValue(10);
		addComponent(thermometer);
		
		manometer = new Manometer(120, 400);
		manometer.setOffsetX(100);
		addComponent(manometer);

		doubleSlider = new DoubleSlider(120, 0, 25, 1, DoubleSlider.HORIZONTAL);
		doubleSlider.setOffsetX(60);
		doubleSlider.setOffsetY(100);
		addComponent(doubleSlider);
		
		
		switchC = new Switch(120, 20) {
			@Override
			public void onAction() {
				
			}

			@Override
			public void offAction() {
				
			}
		};
		
		switchC.setOffsetY(100);
		switchC.setOffsetX(5);
		addComponent(switchC);
		
		textInput = new DoubleField(120, 20, 0, 100, 3);
		textInput.setOffsetY(100);
		textInput.setOffsetX(5);
		addComponent(textInput);
		
		button = new Button(50, 50, "Click me") {
			@Override
			public void doSomething() {
				bulbLEGACY.setValue(bulbLEGACY.getValue() + 10);
			}
			
		};
		
		button.setOffsetY(100);
		
		addComponent(button);

		start(30);
		
	}
	
	
	@Override
	public void update() {
		
		t++;
		
		flask.setValue(doubleSlider.getValue());
		
		manometer.setValue((Math.sin(t / 100) + 1) * 760);
		thermometer.setValue(((Math.sin(t / 100) + 1) * 130 / 2) - 30);
		
		
	}

}