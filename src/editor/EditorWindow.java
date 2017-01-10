package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import lab.component.ClickableArea;
import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.input.ButtonComponent;

public class EditorWindow extends LabComponent {
	
	private static final int DRAG_BAR_HEIGHT = 20;
	
	private static final int RESIZE_DRAG_AREA = 5;
	
	
	private String name;
	private final LabComponent content;
	private final LabComponent dragBar;
	private final ButtonComponent closeButton;
	private ClickableArea dragBarDragArea;
	private ClickableArea WResizeDragArea;
	private ClickableArea SWResizeDragArea;
	private ClickableArea SResizeDragArea;
	private ClickableArea SEResizeDragArea;
	private ClickableArea EResizeDragArea;
	
	public EditorWindow(String name, int width, int height) {
		super(width, height);
		
		this.name = name;
		
		dragBarDragArea = new ClickableArea(this, 0, -DRAG_BAR_HEIGHT, width, DRAG_BAR_HEIGHT);
		
		/*
		WResizeDragArea = new ClickableArea(this, -RESIZE_DRAG_AREA, 0, RESIZE_DRAG_AREA, height - RESIZE_DRAG_AREA);
		SWResizeDragArea = new ClickableArea(this, -RESIZE_DRAG_AREA, height - RESIZE_DRAG_AREA, RESIZE_DRAG_AREA, height);
		
		SResizeDragArea = new ClickableArea(this, RESIZE_DRAG_AREA, , RESIZE_DRAG_AREA, height);
		*/
		
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
		closeButton.setOffsetY(1);
		
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
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y - DRAG_BAR_HEIGHT, width, DRAG_BAR_HEIGHT);
		
		g.setColor(Color.white);
		drawCenteredString(g, name, x + width / 2, y - DRAG_BAR_HEIGHT / 2);
		
		g.setColor(Color.gray);
		g.drawRect(x, y, width, height);
		
		
		
		dragBarDragArea.check(x, y, width, height);
		
		if (dragBarDragArea.hasDrag()) {
			
			Point drag = dragBarDragArea.getDragDelta();
			
			if (drag.x != getOffsetX() || drag.y != getOffsetY()) {
				setOffsetX(drag.x);
				setOffsetY(drag.y);

				redrawInputs();
			}
			
		}
		
		
		
		
	}

	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {
		dragBarDragArea.initializeMouseListeners(panel);
	}
	
	
	
	
	
	

}
