package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class DropdownMenu<E> extends InputComponent implements ActionListener {

	private final JComboBox<E> dropdown;
	
	@SafeVarargs
	public DropdownMenu(int width, int height, E... args) {
		super(width, height);
		
		dropdown = new JComboBox<E>(args);
		
		dropdown.addActionListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		onSelectItem((E) ((JComboBox<E>) e.getSource()).getSelectedItem());
	}
	
	public void onSelectItem(E item) {
		
	}

	@Override
	public JComboBox<E> getJComponent() {
		return dropdown;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E getValue() {
		return (E) dropdown.getSelectedItem();
	}

	@Override
	public void setValue(Object v) {
		dropdown.setSelectedItem(v);
	}
	
}
