package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.input.ButtonComponent;

public class EditorWindow extends LabComponent implements MouseListener, MouseMotionListener {
	
	private static final int DRAG_BAR_HEIGHT = 20;
	
	private String name;
	private boolean isBeingDragged = false;
	private final LabComponent content;
	private final LabComponent dragBar;
	private final ButtonComponent closeButton;
	
	public EditorWindow(String name, int width, int height) {
		super(width, height);
		
		this.name = name;
		
		dragBar = new EmptyComponent(width, DRAG_BAR_HEIGHT);
		dragBar.setOffsetY(-DRAG_BAR_HEIGHT);
		
		final EditorWindow t = this;
		
		closeButton = new ButtonComponent(20, 20, "X") {
			@Override
			public void doSomething() {
				t.setVisible(false);
			}
		};
		
		closeButton.setOffsetX(width - closeButton.getWidth());
		closeButton.setOffsetY(0);
		
		dragBar.addChild(closeButton);
		
		content = new EmptyComponent(width, height);
		
		addChild(dragBar);
		addChild(content);
		
		setLayout(FREE_FORM);
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LabComponent getContent() {
		return content;
	}

	private Point relativeClick = null;
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y - DRAG_BAR_HEIGHT, width, DRAG_BAR_HEIGHT);
		
		g.setColor(Color.white);
		drawCenteredString(g, name, x + width / 2, y - DRAG_BAR_HEIGHT / 2);
		
		g.setColor(Color.gray);
		g.drawRect(x, y, width, height);
		
		
		if (!isBeingDragged && click != null) {
			
			double mx = click.getX(), my = click.getY();
			
			if (my < y && my > y - DRAG_BAR_HEIGHT && mx > x && mx < x + width) {
				
				isBeingDragged = true;
				
				relativeClick = new Point(x - click.x, y - click.y);
				
			}
				
			
		}
		
		if (click == null) {
			isBeingDragged = false;
		}
		
		if (isBeingDragged && mousePosition != null) {
			
			if (mousePosition.x + relativeClick.x != getOffsetX() || mousePosition.y + relativeClick.y != getOffsetY()) {
				setOffsetX(mousePosition.x + relativeClick.x);
				setOffsetY(mousePosition.y + relativeClick.y);
				
				redrawInputs();
			}
			
		}
		
	}

	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	
	
	
	
	private Point mousePosition = null;
	private Point click = null;
	
	@Override
	public void mousePressed(MouseEvent e) {
		click = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		click = null;
		mousePosition = null;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (click != null) {
			mousePosition = e.getPoint();
		}
	}



	@Override
	public void mouseClicked(MouseEvent arg0) { }
	@Override
	public void mouseEntered(MouseEvent arg0) { }
	@Override
	public void mouseExited(MouseEvent arg0) { }
	@Override
	public void mouseMoved(MouseEvent arg0) { }

}
