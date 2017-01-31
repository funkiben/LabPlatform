package test;

import lab.LabFrame;
import lab.component.LabComponent;
import lab.component.container.BulbV2;

public class BulbTestLab extends LabFrame {

	public static void main(String[] args) {
		new BulbTestLab();
	}
	
	BulbV2 bulb;
	
	public BulbTestLab() {
		super("Bulb Test Lab", 800, 800);
		
	
		bulb = new BulbV2(400, 400);
		bulb.setOffsetX(200);
		bulb.setOffsetY(200);
		addComponent(bulb);
		
		start(60);
		
	}

	@Override
	public void update() {
		
	}

}
