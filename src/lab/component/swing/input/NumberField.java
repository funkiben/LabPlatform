package lab.component.swing.input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import lab.SigFig;
import lab.component.swing.Label;

public class NumberField extends TextField implements FocusListener {

	private double min;
	private double max;
	private Label errorLabel;
	private final int sigfigs;
	private final int scientificNotationMinPower;
	
	public NumberField(int width, double min, double max, int sigfigs, int scientificNotationMinPower, double value) {
		super(width, 20, Double.toString(value));
		
		this.min = min;
		this.max = max;
		this.sigfigs = sigfigs;
		this.scientificNotationMinPower = scientificNotationMinPower;
		
		errorLabel = new Label(width, 20);
		errorLabel.setOffset(0, 10);
		errorLabel.setColor(Color.red);
		errorLabel.setFontSize(9);
		
		setScaleChildren(false);
		addChild(errorLabel);
		
		this.getJComponent().addFocusListener(this);
	}
	
	public NumberField(int width, double min, double max, int sigfigs, int scientificNotationMinPower) {
		this(width, min, max, sigfigs, scientificNotationMinPower, 0);
	}
	
	public NumberField(int width, double min, double max, int sigfigs) {
		this(width, min, max, sigfigs, 1);
	}
	
	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
	
	public int getSigFigs() {
		return sigfigs;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(getText());
	}
	
	private void check() {
		try {
			getDoubleValue();
		} catch (NumberFormatException ex) {
			errorLabel.setText("<html><p>Value must be a number.</p></html>");
			return;
		}
		
		if (getDoubleValue() > max || getDoubleValue() < min) {
			errorLabel.setText("<html><p>Value must be between " + min + " and " + max + ".</p></html>");
			return;
		}
		
		errorLabel.setText("");
		
		setText(SigFig.sigfigalize(getDoubleValue(), sigfigs, scientificNotationMinPower));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		check();
		
		onChanged();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		check();
	}

}
