package test;

import lab.LabFrame;
import lab.component.swing.input.NumberFieldComponent;
import lab.component.swing.input.TextFieldComponent;

public class InputTestLab extends LabFrame {
	
	private static final long serialVersionUID = 1761957305529612612L;

	public static void main(String[] args) {
		new InputTestLab();
	}
	
	
	public InputTestLab() {
		super("Input Test Lab", 800, 800);
		
	
		addComponent(new NumberFieldComponent(100, 20, "####"));
		addComponent(new NumberFieldComponent(100, 20, "####"));
		addComponent(new NumberFieldComponent(100, 20, "####"));
		
		
		addComponent(new TextFieldComponent(100, 20, "Hey"));
		
		
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
