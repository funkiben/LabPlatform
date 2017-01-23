package lab.component.input;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

public class TextFieldComponent extends InputComponent {

	private int minValue = 0;
	private int maxValue = 0;
	private final JFormattedTextField textField;

	public TextFieldComponent(int width, int height, int nonDecimal, int decimal) {

		super(width, height);
		textField = new JFormattedTextField(createFormatter(buildDecimal(nonDecimal, decimal)));
		textField.setColumns(10);
	}

	protected MaskFormatter createFormatter(String s) {

		MaskFormatter formatter = null;

		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("Formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}

		return formatter;
	}

	public double getValue() {
		try {
			return Double.parseDouble(textField.getText());
		} catch(Exception e) {
			System.out.println("Error: String cannot be parsed as double.");
			return 0;
		}
	}
	
	public JFormattedTextField getTextField() {
		return textField;
	}

	public void setText(String s) {
		try {
			textField.setValue(s);
		} catch(Exception e) {
			textField.setValue(0);
		}
	}

	public void setMinMax(int min, int max) {
		minValue = min;
		maxValue = max;
	}
	
	private String buildDecimal(int n, int d) {

		String s = "";
		for (int i = 0; i < n; i++) {
			s += "#";
		}
		if (d > 0) {
			s += ".";
		}
		for (int i = 0; i < d; i++) {
			s += "#";
		}
		return s;

	}
	
	@Override
	public Component getJComponent() {
		return textField;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

}
