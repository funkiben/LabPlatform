package lab.component.swing;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ScrollLabel extends SwingComponent{

	public static final int HORIZONTAL_SCROLLBAR_ALWAYS = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
	public static final int HORIZONTAL_SCROLLBAR_NEVER = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
	public static final int HORIZONTAL_SCROLLBAR_AS_NEEDED = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

	
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
	
	public void setText(String text){
		label.setText(text);
	}

	public void setHoriztonalScrollBarPolicy(int policy){
		scrollPane.setHorizontalScrollBarPolicy(policy);
	}
}
