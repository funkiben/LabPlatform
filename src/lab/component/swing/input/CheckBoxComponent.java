package lab.component.swing.input;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class CheckBoxComponent extends InputComponent implements ItemListener {

	private final JCheckBox checkBox;
	
	public CheckBoxComponent(int width, int height, String text) {
		super(width, height);
		
		checkBox = new JCheckBox();
		checkBox.setText(text);
		
		checkBox.addItemListener(this);
		
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}

	@Override
	public JCheckBox getJComponent() {
		return checkBox;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			onSelect();
		} else {
			onDeselect();
		}
	}
	
	public void onSelect() {
		
	}
	
	public void onDeselect() {
		
	}
	
	@Override
	public Object getValue() {
		return checkBox.isSelected();
	}

}
