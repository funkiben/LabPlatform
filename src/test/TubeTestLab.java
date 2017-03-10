package test;

import lab.LabFrame;
import lab.component.LabComponent;
import lab.component.Tube;

public class TubeTestLab extends LabFrame {
	
	private static final long serialVersionUID = -8491053328527644801L;

	public static void main(String[] args) {
		new TubeTestLab();
	}
	
	public TubeTestLab() {
		super("Tube Test Lab", 800, 800);
		
		getRoot().setLayout(LabComponent.FREE_FORM);
		
		addComponent(Tube.straight(20, 20, -30.0D, 100));
		addComponent(Tube.angle90(200, 20, 0.0D, 30));
		
		start(30);
		
		
		
	}

	@Override
	public void update() {
		
	}

}
