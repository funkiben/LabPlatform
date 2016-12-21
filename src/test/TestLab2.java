package test;

import lab.LabFrame;

public class TestLab2 extends LabFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TestLab2();
	}
	
	public TestLab2() {
		super("Test Lab 2.0", 800, 800);
		
		addComponent(new RectComponent(800, 800));
		
		start(30);
	}

	@Override
	public void update() {
		
	}

}
