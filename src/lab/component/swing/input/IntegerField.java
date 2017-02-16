package lab.component.swing.input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import lab.component.swing.Label;

public class IntegerField extends TextField implements FocusListener {

	private int min;
	private int max;
	private Label errorLabel;
	
	public IntegerField(int width, int min, int max, int value) {
		super(width, 20, Double.toString(value));
		
		this.min = min;
		this.max = max;
		
		errorLabel = new Label(width, 20);
		errorLabel.setOffset(0, 10);
		errorLabel.setColor(Color.red);
		errorLabel.setFontSize(9);
		
		setScaleChildren(false);
		addChild(errorLabel);
		
		this.getJComponent().addFocusListener(this);
	}
	
	public IntegerField(int width, int min, int max) {
		this(width, min, max, 0);
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public double getIntegerValue() {
		return Integer.parseInt(getText());
	}
	
	private void check() {
		try {
			getIntegerValue();
		} catch (NumberFormatException ex) {
			errorLabel.setText("<html><p>Value must be a number.</p></html>");
			return;
		}
		
		if (getIntegerValue() > max || getIntegerValue() < min) {
			errorLabel.setText("<html><p>Value must be between " + min + " and " + max + ".</p></html>");
			return;
		}
		
		errorLabel.setText("");
		
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
