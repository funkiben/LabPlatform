package lab.component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import draw.Drawable;

public abstract class LabComponent implements Drawable {
	
	private int width;
	private int height;
	private int offsetX = 0;
	private int offsetY = 0;
	private boolean inputsDrawn = false;
	private boolean visible = true;
	private List<LabComponent> children = new ArrayList<LabComponent>();
	
	public LabComponent(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void addChild(LabComponent component) {
		children.add(component);
	}

	public void addChild(LabComponent...components) {
		for (LabComponent component : components) {
			addChild(component);
		}
	}
	
	public void removeChild(LabComponent component) {
		children.remove(component);
	}
	
	public List<LabComponent> getChildren() {
		return new ArrayList<LabComponent>(children);
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public void setInputsDrawn() {
		inputsDrawn = true;
	}
	
	public boolean areInputsDrawn() {
		return inputsDrawn;
	}
	
	public void redrawInputs() {
		inputsDrawn = false;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void update() { // guarenteed to run at set fps
		
	}
	
	
	private int prevWidth = -1;
	private int prevHeight = -1;
	private boolean redrawInputs = false;
	
	public void draw(Graphics g, int px, int py, int w, int h, JPanel canvas, boolean overMaxFPS) {
		if (!overMaxFPS) {
			update();
		}
		
		draw(px, py, w, h, g);
		
		int maxHeight = Integer.MIN_VALUE;
		int x = 0, y = 0, swidth, sheight, sx, sy;
		
		for (LabComponent c : children) {
			
			if (x + c.getWidth() + c.getOffsetX() > width) {
				if (maxHeight == Integer.MIN_VALUE) {
					maxHeight = 0;
				}
				
				y += maxHeight + c.getOffsetY();
				maxHeight = Integer.MIN_VALUE;
				x = 0;
			}
			
			swidth = (int) ((double) c.getWidth() / width * w);
			sheight = (int) ((double) c.getHeight() / height * h);
			sx = (int) ((double) (x + c.getOffsetX()) / width * w) + px;
			sy = (int) ((double) (y + c.getOffsetY()) / height * h) + py;
			
			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, canvas, overMaxFPS);
				
			}
			
			if (!overMaxFPS) {
				c.update();
			}
			
			if (!c.areInputsDrawn() || redrawInputs) {
				c.drawInputs(sx, sy, swidth, sheight, canvas);
				c.setInputsDrawn();
			}
			
			x += c.getWidth() + c.getOffsetX();
			
			if (c.getHeight() + c.getOffsetY() > maxHeight) {
				maxHeight = c.getHeight() + c.getOffsetY();
			}
			
		}
		
		redrawInputs = false;
		
		if (prevWidth == -1) {
			prevWidth = canvas.getWidth();
			prevHeight = canvas.getHeight();
		} else {
			
			if (prevWidth != canvas.getWidth() || prevHeight != canvas.getHeight()) {
				redrawInputs = true;
			}
			
			prevWidth = canvas.getWidth();
			prevHeight = canvas.getHeight();
		}
		
	}
	
	public abstract void drawInputs(int x, int y, int width, int height, JPanel panel);
	
	
}
