package test;

import lab.LabFrame;
import lab.component.sensor.PressureGauge;

public class GaugeTestLab extends LabFrame{
	

	private static final long serialVersionUID = 7321300459079955237L;

	public static void main(String[] args) {
		new GaugeTestLab();
	}
	
	private final PressureGauge pressureGauge;
	
	public GaugeTestLab() {
		super("Gauge Test Lab", 800, 800);
		
		pressureGauge = new PressureGauge(200, 200, "Test Gauge", "kPa", 3);
		pressureGauge.setOffset(50, 50);
		
		addComponent(pressureGauge);
		
		start(30);
	}
	
	int t = 0;
	
	@Override
	public void update() {
		t++;
		
		pressureGauge.setValue((Math.sin(t / 100.0) + 1) * 101.325/2.0);
		
	}
	

}
