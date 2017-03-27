package lab.component.swing.input;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuComponent extends InputComponent{
	private JMenuBar menuBar;
	private HashMap<String, JMenu> menu;
	private HashMap<String, Object> menuItem;
	
	public MenuComponent(int width, int height) {
		super(width, height);
		menuBar = new JMenuBar();
		menu = new HashMap<String, JMenu>();
		menuItem = new HashMap<String, Object>();
	}
	
	public void addMenu(String name){
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		menu.put(name, newMenu);
	}
		
	public void addMenuItem(String text, String menuName){
		JMenuItem newMenuItem = new JMenuItem(text);
		menu.get(menuName).add(newMenuItem);
		menuItem.put(text, newMenuItem);
	}
	
	public void addRadioButtonMenuItem(String text, String menuName, boolean selected){
		JRadioButtonMenuItem newRadioButtonMenuItem = new JRadioButtonMenuItem(text);
		newRadioButtonMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		newRadioButtonMenuItem.setSelected(selected);
		menu.get(menuName).add(newRadioButtonMenuItem);
		menuItem.put(text, newRadioButtonMenuItem);
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
