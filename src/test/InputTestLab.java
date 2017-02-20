package test;

import lab.LabFrame;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.ItemList;

public class InputTestLab extends LabFrame {
	
	private static final long serialVersionUID = 1761957305529612612L;

	public static void main(String[] args) {
		new InputTestLab();
		
	}
	
	
	public InputTestLab() {
		super("Input Test Lab", 800, 800);
		
		
		addComponent(new DoubleField(100, 0, 20, 3, 3));
		
		ItemList<String> list = new ItemList<String>(100, 50) {
			@Override
			public void onValueChanged() {
				System.out.println(getValue());
			}
		};
		list.add("Test 0");
		list.add("Test 1");
		list.add("Test 2");
		list.add("Test 3");
		list.add("Test 4");
		
		addComponent(list);
		
		start(60);
	}

	@Override
	public void update() {
		
	}

}
