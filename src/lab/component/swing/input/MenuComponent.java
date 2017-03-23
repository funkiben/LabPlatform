package lab.component.swing.input;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuComponent extends InputComponent{
	private JMenuBar menuBar;
	private ArrayList<JMenu> menu;
	private ArrayList<JMenuItem> menuItem;
	
	public MenuComponent(int width, int height) {
		super(width, height);
		menuBar = new JMenuBar();
	}
	
	public void addMenu(JMenu newmenu){
		menuBar.add(newmenu);
	}
		
	public void addMenuItem(){
		
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object v) {
		// TODO Auto-generated method stub
		
	}

	public Component getJComponent() {
		return menuBar;
	}
	
	

}
