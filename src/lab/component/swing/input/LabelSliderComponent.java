package lab.component.swing.input;

import lab.SigFig;
import lab.component.swing.LabelComponent;

public class LabelSliderComponent extends SliderComponent {

	private final LabelComponent label;
	private final int sigfigs;
	
	public LabelSliderComponent(int width, int height, float min, float max, float increment, int sigfigs, int orientation) {
		super(width, height, min, max, increment, orientation);
		
		this.sigfigs = sigfigs;
		
		setLayout(FREE_FORM);
		
		label = new LabelComponent(width / 4, height);
		label.setOffsetX(width);
		label.setOffsetY(-10);
		
		addChild(label);
		
	}
	
	public LabelComponent getLabel() {
		return label;
	}
	
	
	
	public void onChange() {
		
	}
	
	@Override
	public void update() {
		label.setText(SigFig.sigfigalize(getFloatValue(), sigfigs));
	}

}
