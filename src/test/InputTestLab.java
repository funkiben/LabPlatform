package test;

import lab.LabFrame;
import lab.component.input.LabelSliderComponent;
import lab.component.input.SliderComponent;

public class InputTestLab extends LabFrame {
	
	private static final long serialVersionUID = 1761957305529612612L;

	public static void main(String[] args) {
		new InputTestLab();
	}
	
	
	public InputTestLab() {
		super("Input Test Lab", 800, 800);
		
		LabelSliderComponent slider = new LabelSliderComponent(300, 100, 0.0f, 1.0f, 0.01f, 3, SliderComponent.HORIZONTAL);
		
		slider.addValueLabel(0.5f, "middle");
		slider.addValueLabel(0.0f, "start");
		slider.addValueLabel(1.0f, "end");
		
		addComponent(slider);
		
		
		
		
		
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
