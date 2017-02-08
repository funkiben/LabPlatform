package lab.component.swing;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends SwingComponent {

	private String labelText;
	private final JLabel label;
	
	public Label(int width, int height, String text) {
		super(width,height);
		
		label = new JLabel();
		labelText = text;
		
	}
	
	public Label(int width, int height) {
		this(width, height, "");
	}
	
	@Override
	public Component getJComponent() {
		return label;
	}
	
	public void setText(String labelText) {
		this.labelText = labelText;
		label.setText(labelText);
	}
	
	public String getText() {
		return labelText;
	}
	
	public int getTextWidth() {
		return label.getFontMetrics(label.getFont()).stringWidth(labelText);
	}
	
	public void setFontSize(int size) {
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
	}
	
	public void setFont(Font font) {
		label.setFont(font);
	}
	
	@Override
	public void update() {
		label.setText(labelText);
	}
	

}
