package test;

import lab.LabFrame;
import lab.component.EmptyComponent;
import lab.component.MinimizableComponent;
import lab.component.container.GraduatedCylinder;
import lab.component.swing.input.NumberField;
import lab.component.swing.input.TextField;

public class InputTestLab extends LabFrame {
	
	private static final long serialVersionUID = 1761957305529612612L;

	public static void main(String[] args) {
		new InputTestLab();
		
	}
	
	
	public InputTestLab() {
		super("Input Test Lab", 800, 800);
		
		
		MinimizableComponent mc = new MinimizableComponent("Test Minimizable Component", 300, 500, 15);
		mc.setOffsetX(30);
		mc.setOffsetY(30);
		addComponent(mc);
		
		mc.addChild(new GraduatedCylinder(50, 250));
		mc.addChild(new EmptyComponent(250, 0));
		
		
		mc.addChild(new NumberField(100, 0, 20, 3, 4));
		mc.addChild(new NumberField(100, 0, 20, 3, 4));
		mc.addChild(new NumberField(100, 0, 20, 3, 4));
		
		addComponent(new NumberField(100, 0, 20, 3, 3));
		
		
		mc.addChild(new TextField(100, 20, "Hey"));
		
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
