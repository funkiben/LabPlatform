package lab.component.input;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SwitchComponent extends InputComponent implements ChangeListener {

	private final JCheckBox switchField;
	private final JLabel valueField;
	private String onTextString;
	private String offTextString;
	private boolean showValue;

	public SwitchComponent(int width, int height, String onText, String offText, boolean showValueField) {
		
		super(width, height);
		
		switchField = new JCheckBox(new ImageIcon("src/resources/offSwitch.png", offText));
		switchField.setSelectedIcon(new ImageIcon("src/resources/onSwitch.png", onText));
		
		valueField = new JLabel();
		showValue = showValueField;

		onTextString = onText;
		offTextString = offText;

	}

	public void onAction() {
		
	}

	public void offAction() {
		
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {

	}

	@Override
	public void drawJComponents(int x, int y, int width, int height, JPanel panel) {
		
		if (showValue) {
			
			panel.add(switchField);
			
			switchField.setSize(90, 35);
			switchField.setLocation(x, y);
			switchField.addChangeListener(this);
			switchField.setEnabled(this.isActivated());
			
			panel.add(valueField);
			
			valueField.setSize(width - 90, height);
			valueField.setLocation(x + 90, y);
			valueField.setVisible(true);
			
		} else {
			
			panel.add(switchField);
			switchField.setSize(90, 35);
			switchField.setLocation(x, y);
			switchField.addChangeListener(this);
			switchField.setEnabled(this.isActivated());
			
		}
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		JCheckBox source = (JCheckBox) e.getSource();
		
		if (source.isSelected()) {
			
			onAction();
			valueField.setText(onTextString);
			
		} else {
			
			offAction();
			valueField.setText(offTextString);
			
		}
	}
	
	@Override
	public Component getJComponent() {
		return switchField;
	}

}
