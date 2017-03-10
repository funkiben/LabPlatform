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
		
		PressureGauge gauge1 = new PressureGauge(200,200,"Pressure of HI","mmHg",5);
		gauge1.setOffset(50, 50);
		addComponent(gauge1);
		
		PressureGauge gauge2 = new PressureGauge(200,200,"Pressure of HI","mmHg",5);
		gauge2.setOffset(50, 50);
		addComponent(gauge2);
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
