package lab.component;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class ClickableArea implements MouseInputListener, MouseMotionListener {
	
	private int x, y, width, height;
	private final LabComponent component;
	private Point mousePosition = null;
	private Point clickPosition = null;
	private Point relativeClick = null;
	private boolean hasClick = false;
	private boolean hasDrag = false;
	private boolean scale = false;
	
	public ClickableArea(LabComponent component, int x, int y, int width, int height) {
		this.component = component;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public ClickableArea(LabComponent component) {
		this(component, 0, 0, 0, 0);
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

	public Point getClickPosition() {
		return clickPosition;
	}

	public boolean hasClick() {
		return hasClick;
	}

	public boolean hasDrag() {
		return hasDrag;
	}
	
	public Point getDragDelta() {
		return new Point(mousePosition.x + relativeClick.x, mousePosition.y + relativeClick.y);
	}
	
	public LabComponent getComponent() {
		return component;
	}

	public boolean canScale() {
		return scale;
	}
	
	public void setScale(boolean scale) {
		this.scale = scale;
	}
	
	public void initializeMouseListeners(JPanel panel) {
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	public void check(int sx, int sy, int sw, int sh) {
		
		hasClick = false;
		
		if (clickPosition != null) {
			
			double bx, by, bw, bh;
			
			if (sw != component.getWidth() && sh != component.getHeight() && scale) {
			
				bx = ((double) x / component.getWidth()) * sw + sx;
				by = ((double) y / component.getHeight()) * sh + sy;
				
				bw = (double) width / component.getWidth() * sw;
				bh = (double) height / component.getHeight() * sh;
				
			} else {
				bx = x + sx;
				by = y + sy;
				bw = width;
				bh = height;
			}
			
			
			if (clickPosition.x >= bx && clickPosition.x <= bx + bw && clickPosition.y >= by && clickPosition.y <= by + bh) {
				hasClick = true;
				hasDrag = true;
				relativeClick = new Point(sx - clickPosition.x, sy - clickPosition.y);
			}
			
		}
		
		
		
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		clickPosition = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		hasDrag = false;
		hasClick = false;
		clickPosition = null;
		mousePosition = null;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (clickPosition != null) {
			mousePosition = e.getPoint();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
	}


	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	
	
}