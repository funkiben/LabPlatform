package lab.component;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import draw.Drawable;

public abstract class LabComponent implements Drawable {
	
	public static final byte FREE_FORM = 0;
	public static final byte PARAGRAPH = 1;
	
	private int width;
	private int height;
	private int offsetX = 0;
	private int offsetY = 0;
	private int zOrder = 0;
	private boolean inputsDrawn = false;
	private boolean visible = true;
	private int layout = PARAGRAPH;
	private boolean scaleChildren = true;
	private List<LabComponent> children = new ArrayList<LabComponent>();
	private LabComponent parent = null;
	private LabComponent root = null;
	private boolean needsChildSort = false;
	private int lastDrawX = 0;
	private int lastDrawY = 0;
	private int lastDrawWidth = 0;
	private int lastDrawHeight = 0;
	
	public LabComponent(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	private void updateRoot() {
		root = getRoot(this);
		
		for (LabComponent c : children) {
			c.updateRoot();
		}
	}
	
	public void addChild(LabComponent component) {
		component.parent = this;
		component.updateRoot();
		
		children.add(component);
		
		needsChildSort = true;
	}

	public void addChild(LabComponent...components) {
		for (LabComponent component : components) {
			addChild(component);
		}
	}
	
	public void removeChild(LabComponent component) {
		children.remove(component);
		component.parent = null;
		component.updateRoot();
		
		needsChildSort = true;
	}
	
	public List<LabComponent> getChildren() {
		return new ArrayList<LabComponent>(children);
	}
	
	public LabComponent getParent() {
		return parent;
	}
	
	public LabComponent getRoot() {
		return root;
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
	
	public int getZOrder() {
		return zOrder;
	}

	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
		
		if (parent != null) {
			parent.needsChildSort = true;
		}
	}
	
	public void setInputsDrawn() {
		inputsDrawn = true;
	}
	
	public boolean areInputsDrawn() {
		return inputsDrawn;
	}
	
	public void redrawInputs() {
		inputsDrawn = false;
		
		for (LabComponent child : children) {
			child.redrawInputs();
		}
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		
		for (LabComponent child : children) {
			child.setVisible(visible);
		}
	}
	
	public int getLayout() {
		return layout;
	}
	
	public void setLayout(int layout) {
		this.layout = layout;
	}
	
	public boolean canScaleChildren() {
		return scaleChildren;
	}
	
	public void setScaleChildren(boolean scaleChildren) {
		this.scaleChildren = scaleChildren;
	}

	public int getLastDrawX() {
		return lastDrawX;
	}

	public void setLastDrawX(int lastDrawX) {
		this.lastDrawX = lastDrawX;
	}

	public int getLastDrawY() {
		return lastDrawY;
	}

	public void setLastDrawY(int lastDrawY) {
		this.lastDrawY = lastDrawY;
	}

	public int getLastDrawWidth() {
		return lastDrawWidth;
	}

	public void setLastDrawWidth(int lastDrawWidth) {
		this.lastDrawWidth = lastDrawWidth;
	}

	public int getLastDrawHeight() {
		return lastDrawHeight;
	}

	public void setLastDrawHeight(int lastDrawHeight) {
		this.lastDrawHeight = lastDrawHeight;
	}

	public boolean isPointCovered(int x, int y, int z) {
		for (LabComponent c : children) {
			if (c.contains(x, y)) {
				if (c.getZOrder() > z) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean contains(int x, int y) {
		return x >= lastDrawX && x <= lastDrawX + lastDrawWidth && y >= lastDrawY && y <= lastDrawY + lastDrawHeight;
	}
	
	public void update() { // guarenteed to run at constant fps
		
	}
	
	
	private int prevWidth = -1;
	private int prevHeight = -1;
	private boolean redrawInputs = false;
	
	public void draw(Graphics g, int px, int py, int w, int h, JPanel canvas, boolean overMaxFPS) {
		lastDrawX = px;
		lastDrawY = py;
		lastDrawWidth = w;
		lastDrawHeight = h;
		
		if (!overMaxFPS) {
			update();
		}
		
		if (needsChildSort) {
			sortChildren();
			needsChildSort = false;
		}
		
		draw(px, py, w, h, g);
		
		if (layout == PARAGRAPH) {
			drawParagraph(g, px, py, w, h, canvas, overMaxFPS);
		} else if (layout == FREE_FORM) {
			drawFreeForm(g, px, py, w, h, canvas, overMaxFPS);
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
	
	private void sortChildren() {
		Collections.sort(children, new Comparator<LabComponent>() {
			@Override
			public int compare(LabComponent c1, LabComponent c2) {
				return c1.getZOrder() - c2.getZOrder();
			}
		});
	}
	
	private void drawParagraph(Graphics g, int px, int py, int w, int h, JPanel canvas, boolean overMaxFPS) {
		int maxHeight = Integer.MIN_VALUE;
		int x = 0, y = 0, swidth, sheight, sx, sy;
		
		for (LabComponent c : children) {
			
			if (x + c.getWidth() + c.getOffsetX() > (scaleChildren ? width : w)) {
				if (maxHeight == Integer.MIN_VALUE) {
					maxHeight = 0;
				}
				
				y += maxHeight + c.getOffsetY();
				maxHeight = Integer.MIN_VALUE;
				x = 0;
			}
			
			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w);
				sheight = (int) ((double) c.getHeight() / height * h);
				sx = (int) ((double) (x + c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (y + c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = x + c.getOffsetX() + px;
				sy = y + c.getOffsetY() + py;
			}
			
			if (!c.areInputsDrawn() || redrawInputs) {
				c.drawInputs(sx, sy, swidth, sheight, canvas);
				c.setInputsDrawn();
			}
			
			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, canvas, overMaxFPS);
			}
			
			if (!overMaxFPS) {
				c.update();
			}
			
			x += c.getWidth() + c.getOffsetX();
			
			if (c.getHeight() + c.getOffsetY() > maxHeight) {
				maxHeight = c.getHeight() + c.getOffsetY();
			}
			
		}
	}
	
	private void drawFreeForm(Graphics g, int px, int py, int w, int h, JPanel canvas, boolean overMaxFPS) {
		int swidth, sheight, sx, sy;
		
		for (LabComponent c : children) {
			
			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w);
				sheight = (int) ((double) c.getHeight() / height * h);
				sx = (int) ((double) (c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = c.getOffsetX() + px;
				sy = c.getOffsetY() + py;
			}
			
			if (!c.areInputsDrawn() || redrawInputs) {
				c.drawInputs(sx, sy, swidth, sheight, canvas);
				c.setInputsDrawn();
			}
			
			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, canvas, overMaxFPS);
			}
			
			if (!overMaxFPS) {
				c.update();
			}
			
		}
	}
	
	
	public abstract void drawInputs(int x, int y, int width, int height, JPanel panel);
	
	public static void drawCenteredString(Graphics g, String str, int x, int y) {
		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.stringWidth(str);
		
		g.drawString(str, x - width / 2, y);
		
	}
	
	private static LabComponent getRoot(LabComponent p) {
		if (p.root != null) {
			return p.root;
		}
		
		if (p.parent != null) {
			return getRoot(p.parent);
		} else {
			return p;
		}
	}

	
	
}
