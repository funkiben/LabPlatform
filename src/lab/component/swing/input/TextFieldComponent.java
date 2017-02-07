package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class TextFieldComponent extends InputComponent implements ActionListener {

	private final JTextField textField;

	public TextFieldComponent(int width, int height, String defaultValue) {
		super(width, height);

		textField = new JTextField(defaultValue);
		textField.addActionListener(this);

	}

	public String getText() {
		return textField.getText();
	}
	
	public Object getValue() {
		return textField.getText();
	}

	public void setText(String s) {
		textField.setText(s);
	}
	
	public void setColumns(int c) {
		textField.setColumns(c);
	}
	
	@Override
	public JComponent getJComponent() {
		return textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		onChanged();
	}
	
	public void onChanged() {

	}


}
