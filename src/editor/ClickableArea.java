package editor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import lab.component.LabComponent;

public class ClickableArea implements MouseInputListener, MouseMotionListener {
	
	private int x, y, width, height;
	private final LabComponent component;
	private Point mousePosition = null;
	private Point click = null;
	private Point relativeClick = null;
	private Point dragDelta = null;
	private boolean hasClick = false;
	private boolean hasDrag = false;
	
	public ClickableArea(LabComponent component, int x, int y, int width, int height) {
		this.component = component;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getMousePosition() {
		return mousePosition;
	}

	public Point getClick() {
		return click;
	}

	public boolean hasClick() {
		return hasClick;
	}

	public boolean hasDrag() {
		return hasDrag;
	}
	
	public Point getDragDelta() {
		return dragDelta;
	}

	public void initializeMouseListeners(JPanel panel) {
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	public void check(LabComponent c, int sx, int sy, int sw, int sh) {
		
		hasClick = false;
		
		if (click != null) {
			
			double cx = (click.getX() - x) / width * sw + sx;
			double cy = (click.getY() - y) / height * sh + sy;
			
			if (cx >= x && cx <= x + width && cy >= y && cy <= y + height) {
				hasClick = true;
				hasDrag = true;
				relativeClick = new Point(click.x - x, click.y - y);
			}
			
		}
		
		if (hasDrag) {
			dragDelta = new Point(mousePosition.x + relativeClick.x, mousePosition.y + relativeClick.y);
		}
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		click = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		click = null;
		mousePosition = null;
		hasDrag = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (click != null) {
			mousePosition = e.getPoint();
		}
	}



	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	@Override
	public void mouseMoved(MouseEvent e) { }
	
}