package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextField extends InputComponent implements ActionListener {

	private final JTextField textField;

	public TextField(int width, String defaultValue) {
		super(width, 20);

		textField = new JTextField(defaultValue);
		textField.addActionListener(this);

	}
	
	public TextField(int width) {
		this(width, "");
	}

	public String getText() {
		return textField.getText();
	}
	
	@Override
	public Object getValue() {
		return textField.getText();
	}
	
	@Override
	public void setValue(Object v) {
		textField.setText(v.toString());
	}

	public void setText(String s) {
		textField.setText(s);
	}
	
	public void setColumns(int c) {
		textField.setColumns(c);
	}
	
	@Override
	public JTextField getJComponent() {
		return textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		onChanged();
	}
	
	public void onChanged() {

	}


}
