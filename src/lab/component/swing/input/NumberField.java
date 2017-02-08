package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class NumberField extends InputComponent implements ActionListener {

	private final JFormattedTextField textField;

	public NumberField(int width, int height, String format) {
		super(width, height);

		textField = new JFormattedTextField(createFormatter(format));
		textField.setColumns(format.length());
		textField.addActionListener(this);

	}

	public NumberField(int width, int height, int nonDecimal, int decimal) {
		this(width, height, buildDecimal(nonDecimal, decimal));
	}

	protected MaskFormatter createFormatter(String s) {

		MaskFormatter formatter = null;

		try {
			formatter = new MaskFormatter(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatter;
	}

	public double getDoubleValue() {
		return (double) textField.getValue();
	}
	
	public Object getValue() {
		return getDoubleValue();
	}

	public void setValue(double n) {
		try {
			textField.setValue(n);
		} catch (Exception e) {
			textField.setValue(0);
		}
	}

	@Override
	public JFormattedTextField getJComponent() {
		return textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		onChanged();
	}

	public void onChanged() {

	}

	private static String buildDecimal(int n, int d) {

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

}
