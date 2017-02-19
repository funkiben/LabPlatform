package lab.component.swing.input;

import lab.component.swing.Label;

public class LabeledIntegerSlider extends IntegerSlider {

	private final Label label;

	public LabeledIntegerSlider(int width, int min, int max, int orientation) {
		super(width, min, max, orientation);
		
		setLayout(FREE_FORM);
		
		label = new Label(width / 4, 20);
		label.setOffset(width, 0);
		
		addChild(label);
		
	}
	
	public Label getLabel() {
		return label;
	}
	
	
	
	public void onChange() {
		
	}
	
	@Override
	public void update() {
		label.setText(getValue().toString());
	}

}
