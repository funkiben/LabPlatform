package lab.component.input;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import lab.component.LabComponent;

public abstract class DropdownComponent extends LabComponent implements ActionListener {

	private JComboBox<String> dropdown;
	private ArrayList<String> dropdownList;

	public DropdownComponent(int width, int height, String... args) {
		super(width, height);
		
		dropdownList = new ArrayList<String>();
		
		for (String c : args) {
			dropdownList.add(c);
		}
		
		dropdown = new JComboBox<String>(args);

	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {

	}

	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {
		dropdown.setSize(width, height);
		dropdown.setLocation(x, y);
		dropdown.addActionListener(this);
		panel.add(dropdown);
	}

	public JComboBox<String> getDropdown() {
		return dropdown;
	}

	public void setDropdown(JComboBox<String> dropdown) {
		this.dropdown = dropdown;
	}

	public ArrayList<String> getDropdownList() {
		return dropdownList;
	}

	public void setDropdownList(ArrayList<String> dropdownList) {
		this.dropdownList = dropdownList;
	}

}
