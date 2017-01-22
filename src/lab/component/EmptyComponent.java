package lab.component;

import java.awt.Graphics;

import javax.swing.JPanel;

public class EmptyComponent extends LabComponent {

	public EmptyComponent(int width, int height) {
		super(width, height);
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) { }

	@Override
	public void drawJComponents(int x, int y, int width, int height, JPanel panel) { }

}
