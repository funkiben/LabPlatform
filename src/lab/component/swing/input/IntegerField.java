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
		
		errorLabel = new Label(100, 20);
		errorLabel.setOffset(width, 0);
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
	
	@Override
	public Integer getValue() {
		try {
			return Integer.parseInt(getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	private void check() {
		try {
			Integer.parseInt(getText());
		} catch (NumberFormatException ex) {
			errorLabel.setText("<html><p>Value must be a number.</p></html>");
			return;
		}
		
		if (getValue() > max || getValue() < min) {
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
