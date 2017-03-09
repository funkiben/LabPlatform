package test;

import lab.LabFrame;
import lab.component.ImageComponent;

public class ImageComponentTestLab extends LabFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ImageComponentTestLab();
	}

	public ImageComponentTestLab() {
		super("ImageComponent Test Lab", 800, 800);
		getRoot().setLayout(0);
		ImageComponent image = new ImageComponent(500, 100, "/flask.gif");
		image.setShowBounds(true);
		image.setOffset(80, 200);
		
		addComponent(image);
		start(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
