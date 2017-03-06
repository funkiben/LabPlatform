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

		ImageComponent image = new ImageComponent("/flask.gif");
		addComponent(image);
		start(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
