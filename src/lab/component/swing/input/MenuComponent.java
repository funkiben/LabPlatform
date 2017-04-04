package lab.component.swing.input;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

//creates a file menu at the top of a window
public class MenuComponent extends InputComponent {

	// create menu bar and maps for sub menus
	private JMenuBar menuBar;
	private Map<String, JMenu> menu;
	private Map<String, JMenuItem> menuItem;

	// initialize menu bar and maps
	public MenuComponent(int width, int height) {
		super(width, height);
		menuBar = new JMenuBar();
		menu = new HashMap<String, JMenu>();
		menuItem = new HashMap<String, JMenuItem>();
	}

	// add a menu to the menu bar
	public void addMenu(String name) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		menu.put(name, newMenu);
	}

	// add a menu item to the menu
	public void addMenuItem(String text, String menuName, ActionListener e) {
		JMenuItem newMenuItem = new JMenuItem(text);
		newMenuItem.addActionListener(e);
		menu.get(menuName).add(newMenuItem);
		menuItem.put(text, newMenuItem);
	}

	// add a radio button menu item to a menu
	public void addRadioButtonMenuItem(String text, String menuName, boolean selected, ActionListener e) {
		JRadioButtonMenuItem newRadioButtonMenuItem = new JRadioButtonMenuItem(text);
		newRadioButtonMenuItem.addActionListener(e);
		newRadioButtonMenuItem.setSelected(selected);
		menu.get(menuName).add(newRadioButtonMenuItem);
		menuItem.put(text, newRadioButtonMenuItem);
	}

	// set a menu item's text
	public void setMenuItemText(String menuItemName, String text) {
		menuItem.get(menuItemName).setText(text);
	}

	// get a menu item's text
	public String getMenuItemText(String menuItemName) {
		return menuItem.get(menuItemName).getText();
	}

	// set a radio button to selected
	public void setRadioButtonSelected(String menuItemName, boolean selected) {
		menuItem.get(menuItemName).setSelected(selected);
	}

	// return if the the radio button is selected
	public boolean radioButtonSelected(String menuItemName) {
		return menuItem.get(menuItemName).isSelected();
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setValue(Object v) {

	}

	public Component getJComponent() {
		return menuBar;
	}

}
