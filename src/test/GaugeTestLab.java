package test;

import lab.LabFrame;
import lab.component.EmptyComponent;
import lab.component.container.Bulb;
import lab.component.data.Graph;
import lab.component.data.GraphDataSet;
import lab.component.sensor.PressureGauge;
import lab.component.sensor.Thermometer;
import lab.component.swing.input.Button;
import lab.component.swing.input.Dropdown;
import lab.util.HorizontalGraduation;
import lab.util.VerticalGraduation;

public class GaugeTestLab extends LabFrame{
	
	private Bulb bulbH2;
	private Bulb bulbI2;
	private Bulb bulbHI;
	private Bulb bulbReaction;
	
	private Graph pressureTime;
	
	private PressureGauge pressureI2;
	private PressureGauge pressureH2;
	
	private Dropdown<String> tempDropdown;
	
	private Button setTemperature;
	
	private GraphDataSet h2Set;
	private GraphDataSet i2Set;
	
	private Thermometer thermometer;
	
	private double reactionTemperature;
	
	private static final long serialVersionUID = 7321300459079955237L;

	public static void main(String[] args) {
		new GaugeTestLab();
	}
	
	
	public GaugeTestLab() {
		super("Gauge Test Lab", 1400, 900);
		
		
		
		getRoot().setScaleChildren(true);
		
		EmptyComponent bulbContainer = new EmptyComponent(450,300);
		
		bulbH2 = new Bulb(100,100);
		bulbContainer.addChild(bulbH2);
		
		bulbHI = new Bulb(100,100);
		bulbHI.setOffset(-100,150);
		bulbContainer.addChild(bulbHI);
		
		bulbI2 = new Bulb(100,100);
		bulbI2.setOffset(-100,275);
		bulbContainer.addChild(bulbI2);
		
		bulbReaction = new Bulb(250,250);
		bulbContainer.addChild(bulbReaction);
		
		pressureH2 = new PressureGauge(175,175,"Pressure H2","atm",5);
		pressureH2.setOffset(425,-175);
		pressureH2.setFont(pressureH2.getFont().deriveFont(12f));
		pressureH2.setValue(2.45f);
		bulbContainer.addChild(pressureH2);
		
		pressureI2 = new PressureGauge(175,175,"Pressure I2","atm",5);
		pressureI2.setOffset(425,25);
		pressureI2.setFont(pressureI2.getFont().deriveFont(12f));
		pressureI2.setValue(2.45f);
		bulbContainer.addChild(pressureI2);
		
		addComponent(bulbContainer);
		
		pressureTime = new Graph(350, 350, "Pressure vs Time", "Time (s)", "Pressure (atm)", new VerticalGraduation(0,2.45,.5,.1), new HorizontalGraduation(0,10,1,.5f));
		h2Set = new GraphDataSet("H2",true,true);
		i2Set = new GraphDataSet("   & I2",true,true);
		pressureTime.addDataSet(h2Set);
		pressureTime.addDataSet(i2Set);
		pressureTime.setOffsetX(250);
		addComponent(pressureTime);
		
		tempDropdown = new Dropdown<String>(200,50,"298K","500K","700K","1000K") {
			public void onSelectItem(String item) {
				try {
					setTemperature.setEnabled(true);
				} catch(NullPointerException e) {
					System.out.println("Null Pointer Exception Avoided");
				}
			}
		};
		
		tempDropdown.setOffset(700,25);
		tempDropdown.setValue("298K");
		
		thermometer = new Thermometer(350);
		thermometer.setGraduation(new VerticalGraduation(198, 1200, 100, 10));
		thermometer.getGraduation().setSuffix(" K");
		thermometer.setValue(298);
		thermometer.setOffsetY(-375);
		
		reactionTemperature = Double.parseDouble((tempDropdown.getValue() + "").replaceAll("K", ""));
		addComponent(tempDropdown);
		
		setTemperature = new Button(200,50,"Set Temperature") {
			@Override
			public void doSomething() {
				reactionTemperature = Double.parseDouble((tempDropdown.getValue() + "").replaceAll("K", ""));
				this.setEnabled(false);
			}
		};
		setTemperature.setOffsetY(25);
		addComponent(setTemperature);
		
		
		addComponent(thermometer);
		
		start(60);
	}
	
	private static double lerp(double v1, double v2, float f) {
		return ((v2 - v1) * f + v1);
	}
	
	private double reactionTime = 0;
	
	
	
	@Override
	public void update() {
		reactionTime = reactionTime + .015;
		thermometer.setValue(lerp(thermometer.getValue(),reactionTemperature,0.0089f));
		
		h2Set.addPoint(reactionTime, pressureH2.getValue());
		i2Set.addPoint(reactionTime, pressureI2.getValue());
		
	}

}
