package lab.component;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import lab.util.Drawable;

public abstract class LabComponent implements Drawable {
	
	public static final byte FREE_FORM = 0;
	public static final byte PARAGRAPH = 1;
	
	private String identifier = "";
	private int width;
	private int height;
	private int offsetX = 0;
	private int offsetY = 0;
	private int zOrder = 0;
	private boolean visible = true;
	private int layout = PARAGRAPH;
	private boolean scaleChildren = true;
	private final List<LabComponent> children = new ArrayList<LabComponent>();
	private LabComponent parent = null;
	private LabComponent root = null;
	private boolean needsChildSort = false;
	private int lastDrawX = -1;
	private int lastDrawY = -1;
	private int lastDrawWidth = -1;
	private int lastDrawHeight = -1;
	private int scaleReferenceWidth;
	private int scaleReferenceHeight;
	private boolean jPanelInitCalled = false;
	private boolean showBounds = false;
	
	public LabComponent(int width, int height) {
		this.width = width;
		this.height = height;
		
		scaleReferenceWidth = width;
		scaleReferenceHeight = height;
		
	}
	
	private void updateRoot() {
		root = getRoot(this);
		
		for (LabComponent c : children) {
			c.updateRoot();
		}
	}

	public void insertChild(int index, LabComponent...components) {
		for (LabComponent component : components) {
			component.parent = this;
			component.updateRoot();
			
			children.add(index, component);
			
			needsChildSort = true;
		}
	}
	
	public void addChild(LabComponent...components) {
		for (LabComponent component : components) {
			component.parent = this;
			component.updateRoot();
			
			children.add(component);
			
			needsChildSort = true;
		}
	}
	
	public void removeChild(LabComponent...components) {
		for (LabComponent component : components) {
			children.remove(component);
			component.parent = null;
			component.updateRoot();
			
			needsChildSort = true;
		}
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
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public void stretchToNewWidth(int width) {
		this.width = width;
	}
	
	public void stretchToNewHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
		this.scaleReferenceWidth = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
		this.scaleReferenceHeight = height;
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
	
	public void setOffset(int offsetX, int offsetY){
		setOffsetX(offsetX);
		setOffsetY(offsetY);
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

	public int getLastDrawY() {
		return lastDrawY;
	}

	public int getLastDrawWidth() {
		return lastDrawWidth;
	}

	public int getLastDrawHeight() {
		return lastDrawHeight;
	}
	
	public double getWidthScaleRatio() {
		return (double) width / scaleReferenceWidth;
	}
	
	public double getHeightScaleRatio() {
		return (double) height / scaleReferenceHeight;
	}
	
	public void setShowBounds(boolean showBounds) {
		this.showBounds = showBounds;
	}
	
	public boolean canShowBounds() {
		return showBounds;
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
	
	private void sortChildren() {
		Collections.sort(children, new Comparator<LabComponent>() {
			@Override
			public int compare(LabComponent c1, LabComponent c2) {
				return c1.getZOrder() - c2.getZOrder();
			}
		});
	}
	
	public boolean contains(int x, int y) {
		return x >= lastDrawX && x <= lastDrawX + lastDrawWidth && y >= lastDrawY && y <= lastDrawY + lastDrawHeight;
	}
	
	public void update() { // guarenteed to run at constant fps
		
	}
	
	public void initJPanel(JPanel panel) {
		
	}
	
	public void draw(Graphics g, int px, int py, int w, int h, JPanel jPanel, boolean overMaxFPS) {
		if (!jPanelInitCalled) {
			initJPanel(jPanel);
			jPanelInitCalled = true;
		}
		
		if (!overMaxFPS) {
			update();
		}
		
		if (needsChildSort) {
			sortChildren();
			needsChildSort = false;
		}
		
		if (showBounds) {
			g.setColor(Color.black);
			g.drawRect(px, py, w, h);
		}
		
		if (children.size() > 0) {
			if (layout == PARAGRAPH) {
				drawParagraph(g, px, py, w, h, jPanel, overMaxFPS);
			} else if (layout == FREE_FORM) {
				drawFreeForm(g, px, py, w, h, jPanel, overMaxFPS);
			}	
		} else {
			draw(px, py, w, h, g);
		}
		
		lastDrawX = px;
		lastDrawY = py;
		lastDrawWidth = w;
		lastDrawHeight = h;
		
	}
	
	private void drawParagraph(Graphics g, int px, int py, int w, int h, JPanel panel, boolean overMaxFPS) {
		int maxHeight = Integer.MIN_VALUE;
		int x = 0, y = 0, swidth, sheight, sx, sy;
		
		boolean thisDrawn = false;
		
		for (LabComponent c : children) {
			
			if (c.getZOrder() >= 0 && !thisDrawn) {
				thisDrawn = true;
				draw(px, py, w, h, g);
			}
			
			if (x + c.getWidth() + c.getOffsetX() > (scaleChildren ? width : w)) {
				if (maxHeight == Integer.MIN_VALUE) {
					maxHeight = 0;
				}
				
				y += maxHeight + c.getOffsetY();
				maxHeight = Integer.MIN_VALUE;
				x = 0;
			}
			
			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w * getWidthScaleRatio());
				sheight = (int) ((double) c.getHeight() / height * h * getHeightScaleRatio());
				sx = (int) ((double) (x + c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (y + c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = x + c.getOffsetX() + px;
				sy = y + c.getOffsetY() + py;
			}
			
			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, panel, overMaxFPS);
			}
			
			if (!overMaxFPS) {
				c.update();
			}
			
			x += c.getWidth() + c.getOffsetX();
			
			if (c.getHeight() + c.getOffsetY() > maxHeight) {
				maxHeight = c.getHeight() + c.getOffsetY();
			}
			
		}
		
		if (!thisDrawn) {
			draw(px, py, w, h, g);
		}
	}
	
	private void drawFreeForm(Graphics g, int px, int py, int w, int h, JPanel panel, boolean overMaxFPS) {
		int swidth, sheight, sx, sy;
		
		boolean thisDrawn = false;
		
		for (LabComponent c : children) {
			
			if (c.getZOrder() >= 0 && !thisDrawn) {
				thisDrawn = true;
				draw(px, py, w, h, g);
			}
			
			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w * getWidthScaleRatio());
				sheight = (int) ((double) c.getHeight() / height * h * getHeightScaleRatio());
				sx = (int) ((double) (c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = c.getOffsetX() + px;
				sy = c.getOffsetY() + py;
			}
			
			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, panel, overMaxFPS);
			}
			
			if (!overMaxFPS) {
				c.update();
			}
			
		}
		
		if (!thisDrawn) {
			draw(px, py, w, h, g);
		}
	}
	
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
