package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		menu.addMenuItem("Open File", "File", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open file");
			}

		});
		menu.addRadioButtonMenuItem("Yes", "File", false, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("selected");
			}

		});
		menu.addMenu("Hi there");
		menu.addMenuItem("Howdy bud", "Hi there", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("gotta love those flapjacks");
			}
			
		});
		addComponent(menu);
		start(30);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
