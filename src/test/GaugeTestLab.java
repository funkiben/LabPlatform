package test;

import lab.LabFrame;
import lab.component.sensor.PressureGauge;

public class GaugeTestLab extends LabFrame{

	private static final long serialVersionUID = 7321300459079955237L;

	public static void main(String[] args) {
		new GaugeTestLab();
	}
	
	
	public GaugeTestLab() {
		super("Gauge Test Lab", 800, 500);
		
		PressureGauge gauge = new PressureGauge(200,200,"Pressure","mmHg",5);
		gauge.setOffset(100, 100);
		addComponent(gauge);
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
