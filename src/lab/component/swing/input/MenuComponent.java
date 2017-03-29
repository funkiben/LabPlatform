package lab.component.swing.input;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuComponent extends InputComponent {
	private JMenuBar menuBar;
	private Map<String, JMenu> menu;
	private Map<String, JMenuItem> menuItem;

	public MenuComponent(int width, int height) {
		super(width, height);
		menuBar = new JMenuBar();
		menu = new HashMap<String, JMenu>();
		menuItem = new HashMap<String, JMenuItem>();
	}

	public void addMenu(String name) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		menu.put(name, newMenu);
	}

	public void addMenuItem(String text, String menuName, ActionListener e) {
		JMenuItem newMenuItem = new JMenuItem(text);
		newMenuItem.addActionListener(e);
		menu.get(menuName).add(newMenuItem);
		menuItem.put(text, newMenuItem);
	}

	public void addRadioButtonMenuItem(String text, String menuName, boolean selected, ActionListener e) {
		JRadioButtonMenuItem newRadioButtonMenuItem = new JRadioButtonMenuItem(text);
		newRadioButtonMenuItem.addActionListener(e);
		newRadioButtonMenuItem.setSelected(selected);
		menu.get(menuName).add(newRadioButtonMenuItem);
		menuItem.put(text, newRadioButtonMenuItem);
	}
	
	public void setMenuItemText(String menuItemName, String text){
		menuItem.get(menuItemName).setText(text);
	}
	
	public String getMenuItemText(String menuItemName){
		return menuItem.get(menuItemName).getText();
	}
	
	public void setRadioButtonSelected(String menuItemName, boolean selected){
		menuItem.get(menuItemName).setSelected(selected);
	}
	
	public boolean radioButtonSelected(String menuItemName){
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
