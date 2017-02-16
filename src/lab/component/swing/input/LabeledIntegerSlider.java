package lab.component.swing.input;

import lab.SigFig;
import lab.component.swing.Label;

public class LabeledIntegerSlider extends IntegerSlider {

	private final Label label;
	private final int sigfigs;
	
	public LabeledIntegerSlider(int width, int height, int min, int max, int sigfigs, int orientation) {
		super(width, height, min, max, orientation);
		
		this.sigfigs = sigfigs;
		
		setLayout(FREE_FORM);
		
		label = new Label(width / 4, height);
		label.setOffsetX(width);
		label.setOffsetY(-10);
		
		addChild(label);
		
	}
	
	public Label getLabel() {
		return label;
	}
	
	
	
	public void onChange() {
		
	}
	
	@Override
	public void update() {
		label.setText(SigFig.sigfigalize(getValue(), sigfigs));
	}

}
