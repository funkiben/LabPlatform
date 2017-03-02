package test;

import lab.LabFrame;
import lab.component.swing.input.DoubleField;

public class InputTestLab extends LabFrame {
	
	private static final long serialVersionUID = 1761957305529612612L;

	public static void main(String[] args) {
		new InputTestLab();
		
	}
	
	
	public InputTestLab() {
		super("Input Test Lab", 800, 800);
		
		
		addComponent(new DoubleField(100, 0, 20, 3, 3));
		
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
