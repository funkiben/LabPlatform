package test;

import java.awt.Color;

import lab.LabFrame;
import lab.component.container.Bulb;
import lab.component.container.ContentState;

public class BulbTestLab extends LabFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new BulbTestLab();
	}
	
	Bulb bulb;
	
	public BulbTestLab() {
		super("Bulb Test Lab", 800, 800);
		
	
		bulb = new Bulb(400, 400);
		bulb.setOffsetX(200);
		bulb.setOffsetY(200);
		bulb.setContentState(ContentState.LIQUID);
		bulb.setContentColor(Color.green);
		addComponent(bulb);
		
		bulb.setValue(50);
		
		start(60);
		
	}

	@Override
	public void update() {
		
	}

}
