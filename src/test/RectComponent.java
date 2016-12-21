package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import lab.component.LabComponent;

public class RectComponent extends LabComponent implements MouseMotionListener {
	
	public RectComponent(int width, int height) {
		super(width, height);
		
	}
	
	private int lastX;
	private int lastY;
	private int lastWidth;
	private int lastHeight;
	
	private boolean contains(Point p) {
		return p.y > lastY && p.y < lastY + lastHeight && p.x > lastX && p.x < lastX + lastWidth;
	}
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(new Color(255, 0, 0, 50));
		g.fillRect(x, y, width, height);
		
		if (mouseClick != null) {
			double mx = mouseClick.getX(), my = mouseClick.getY();

			if (my > y && my < y + height && mx > x && mx < x + width) {
				
				addChild(new RectComponent(getWidth() / 2, getHeight() / 2));

				
			}
			
		
			
			mouseClick = null;
		}
		
		lastX = x;
		lastY = y;
		lastWidth = width;
		lastHeight = height;

	}

	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {
		panel.addMouseMotionListener(this);
		
	}

	private Point mouseClick = null;
	

	@Override
	public void mouseDragged(MouseEvent arg0) { }

	@Override
	public void mouseMoved(MouseEvent e) {
		
		for (LabComponent c : getChildren()) {
			if (((RectComponent) c).contains(e.getPoint())) {
				return;
			}
		}
		
		mouseClick = e.getPoint();
	}

}
