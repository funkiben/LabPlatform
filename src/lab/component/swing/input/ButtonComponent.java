package lab.component.swing.input;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public abstract class ButtonComponent extends InputComponent implements ActionListener {
	
	private final JButton button;
	
	public ButtonComponent(int width, int height, String text) {
		super(width, height);
		
		button = new JButton(text);
		
		button.addActionListener(this);

	}
	
	public abstract void doSomething();
	
	public void setFontSize(int size) {
		button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
	}
	
	@Override
	public JButton getJComponent() {
		return button;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		doSomething();
	}
	
	@Override
	public Object getValue() {
		return null;
	}


}
