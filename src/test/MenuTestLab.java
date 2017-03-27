package test;

import lab.LabFrame;
import lab.component.LabComponent;
import lab.component.swing.input.MenuComponent;

public class MenuTestLab extends LabFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MenuTestLab();
	}

	public MenuTestLab() {
		super("MenuTestLab", 500, 500);
		getRoot().setLayout(LabComponent.PARAGRAPH);
		MenuComponent menu = new MenuComponent(500, 20);
		menu.addMenu("File");
		menu.addMenuItem("Open File", "File");
		menu.addRadioButtonMenuItem("Yes", "File", false);
		addComponent(menu);
		start(30);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
