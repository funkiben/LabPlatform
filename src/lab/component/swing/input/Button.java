package lab.component.swing.input;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public abstract class Button extends InputComponent implements ActionListener {
	
	private final JButton button;
	
	public Button(int width, int height, String text) {
		super(width, height);
		
		button = new JButton(text);
		
		button.addActionListener(this);
		
		button.setMargin(new Insets(0, 0, 0, 0));
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
