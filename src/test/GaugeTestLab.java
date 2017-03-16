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
	private double H2Concentration = 2.45;
	private double I2Concentration = 2.45;
	private double HIConcentration = 0;
	private double Kp;
	
	private static final long serialVersionUID = 7321300459079955237L;

	public static void main(String[] args) {
		new GaugeTestLab();
	}
	
	
	public GaugeTestLab() {
		super("Gauge Test Lab", 1400, 900);
		
		
		
		getRoot().setScaleChildren(true);
		
		Kp = 617.5;
		
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
				switch((int)reactionTemperature + "") {
				case "298":
					Kp = 617.5;
				break;
				case "500":
					Kp = 131.6;
				break;
				case "700":
					Kp = 68.6;
				break;
				case "1000":
					Kp = 42.1;
				break;
				}
				this.setEnabled(false);
			}
		};
		setTemperature.setEnabled(false);
		setTemperature.setOffsetY(25);
		addComponent(setTemperature);
		
		
		addComponent(thermometer);
		
		start(60);
	}
	
	private static double lerp(double v1, double v2, float f) {
		return ((v2 - v1) * f + v1);
	}
	
	private double reactionTime = 0;
	
	private double Qp() {
		return (HIConcentration * HIConcentration) / (H2Concentration * I2Concentration);
	}
	
	@Override
	public void update() {
		reactionTime = reactionTime + .015;
		pressureI2.setValue(I2Concentration);
		pressureH2.setValue(H2Concentration);
		
		float rate = 0.0089f;
		
		thermometer.setValue(lerp(thermometer.getValue(),reactionTemperature,rate));
		
		h2Set.addPoint(reactionTime, pressureH2.getValue());
		i2Set.addPoint(reactionTime, pressureI2.getValue());
	
		
		double offset;
		double Qc = Qp();

		if (Qc > Kp) { // NEED MORE REACTANT, SHIFT BACKWARD

			// Kc = (HI - 2x)^2 / (H2 + x)(I2 + x)
			// Kc = (HI - 2x)(HI - 2x) / (H2 + x)(I2 + x)

			// Kc = (4x^2 - (4*HI)x + (HI*HI)) / (x^2 + (I2+H2)x + (H2*I2))

			double a1, b1, c1, a2, b2, c2;

			// cross multiply and foil reactants
			a1 = Kp;
			b1 = Kp * (I2Concentration + H2Concentration);
			c1 = Kp * H2Concentration * I2Concentration;

			// foil products
			a2 = 4;
			b2 = -4 * HIConcentration;
			c2 = HIConcentration * HIConcentration;

			double[] zeros = findZeros(a1 - a2, b1 - b2, c1 - c2);
			
			offset = HIConcentration - 2 * zeros[0] > 0 ? zeros[0] : zeros[1];

			
			H2Concentration = lerp(H2Concentration, H2Concentration + offset, rate);
			I2Concentration = lerp(I2Concentration, I2Concentration + offset, rate);
			HIConcentration = lerp(HIConcentration, HIConcentration - 2 * offset, rate);


		} else if (Qc < Kp) { // NEED MORE PRODUCT, SHIFT FORWARD
			
			// Kc = (HI + 2x)^2 / (H2 - x)(I2 - x)
			// Kc = (HI + 2x)(HI + 2X) / (H2 - x)(I2 - x)
			
			// Kc = (4x^2 + (4*HI)x + (HI*HI)) / (x^2 - (H2+I2)x + (HI*I2))
			
			
			double a1, b1, c1, a2, b2, c2;

			// cross multiply and foil reactants
			a1 = Kp;
			b1 = Kp * -(I2Concentration + H2Concentration);
			c1 = Kp * H2Concentration * I2Concentration;

			// foil products
			a2 = 4;
			b2 = 4 * HIConcentration;
			c2 = HIConcentration * HIConcentration;

			double[] zeros = findZeros(a1 - a2, b1 - b2, c1 - c2);

			
			offset = H2Concentration - zeros[0] > 0 && I2Concentration - zeros[0] > 0 ? zeros[0] : zeros[1];
			
			H2Concentration = lerp(H2Concentration, H2Concentration - offset, rate);
			I2Concentration = lerp(I2Concentration, I2Concentration - offset, rate);
			HIConcentration = lerp(HIConcentration, HIConcentration + 2 * offset, rate);

			
		}
		
	}
	
	private static double[] findZeros(double a, double b, double c) {
		double s = Math.sqrt((b * b) - 4 * a * c);

		double[] zeros = new double[2];
		zeros[0] = (-b + s) / (2 * a);
		zeros[1] = (-b - s) / (2 * a);

		return zeros;
	}

}
