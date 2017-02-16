package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class DropdownMenu extends InputComponent implements ActionListener {

	private final JComboBox<String> dropdown;
	
	public DropdownMenu(int width, int height, String... args) {
		super(width, height);
		
		dropdown = new JComboBox<String>(args);
		
		dropdown.addActionListener(this);
	}
	
	public String getSelectedItem() {
		return (String) dropdown.getSelectedItem();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		onSelectItem((String) ((JComboBox<String>) e.getSource()).getSelectedItem());
	}
	
	public void onSelectItem(String item) {
		
	}

	@Override
	public JComboBox<String> getJComponent() {
		return dropdown;
	}
	
	@Override
	public String getValue() {
		return getSelectedItem();
	}

	@Override
	public void setValue(Object v) {
		dropdown.setSelectedItem(v);
	}
	
}
