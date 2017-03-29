package test;

import lab.LabFrame;
import lab.component.swing.input.slider.LabeledIntegerSlider;
import lab.component.swing.input.slider.Slider;

public class SliderTestLab extends LabFrame {


	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new SliderTestLab();
	}
	
	
	private final Slider slider;
	
	public SliderTestLab() {
		super("Slider Test Lab", 800, 800);
		
		slider = new LabeledIntegerSlider(500, 0, 100, Slider.VERTICAL);
		slider.setOffset(100, 100);
		
		addComponent(slider);
		
		start(30);
		
	}

	@Override
	public void update() {
		
	}

	

}
