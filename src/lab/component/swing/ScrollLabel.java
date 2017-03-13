package lab.component.swing;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ScrollLabel extends SwingComponent{

	private JLabel label;
	private JScrollPane scrollPane;
	
	public ScrollLabel(int width, int height, String text) {
		super(width, height);
		label = new JLabel(text);
		scrollPane = new JScrollPane(label);
	}

	@Override
	public Component getJComponent() {
		return scrollPane;
	}

}
