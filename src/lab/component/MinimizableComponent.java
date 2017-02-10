package lab.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import draw.animation.Animator;
import draw.animation.DoubleLinearAnimation;
import lab.component.LabComponent;

public class MinimizableComponent extends LabComponent {

	private static final int MINIMIZED_HEIGHT = 30;
	
	private final EmptyComponent content;
	private final String title;
	private final ClickableArea clickArea;
	private boolean isMinimized = false;
	private final int expandedHeight;
	private final Animator animator = new Animator();
	
	public MinimizableComponent(String title, int width, int height) {
		super(width, height);

		this.title = title;

		content = new EmptyComponent(width, height - MINIMIZED_HEIGHT);
		content.setOffsetY(MINIMIZED_HEIGHT);
		super.addChild(content);
		
		setShowBounds(true);
		
		expandedHeight = height;
		
		clickArea = new ClickableArea(this, 0, 0, width, MINIMIZED_HEIGHT);
	}

	public String getTitle() {
		return title;
	}
	
	public boolean isMinimized() {
		return isMinimized;
	}
	
	public void setMinimized(boolean isMinimized) {
		this.isMinimized = isMinimized;
		
		if (isMinimized) {
			
			animator.addAnimation("stretch", new DoubleLinearAnimation((double) MINIMIZED_HEIGHT, 25.0) {
				
				@Override
				public void setValue(Double d) {
					stretchToNewHeight(d.intValue());
				}
				
				@Override
				public Double getValue() {
					return (double) getHeight();
				}
				
			});
			
		} else {
			
			animator.addAnimation("stretch", new DoubleLinearAnimation((double) expandedHeight, 25.0) {
				
				@Override
				public void setValue(Double d) {
					stretchToNewHeight(d.intValue());
				}
				
				@Override
				public Double getValue() {
					return (double) getHeight();
				}
				
			});
			
		}
		
	}
	
	@Override
	public void addChild(LabComponent component) {
		content.addChild(component);
	}

	@Override
	public void addChild(LabComponent... components) {
		content.addChild(components);
	}

	@Override
	public void removeChild(LabComponent component) {
		content.removeChild(component);
	}

	@Override
	public List<LabComponent> getChildren() {
		return content.getChildren();
	}

	@Override
	public void draw(int x, int y, int w, int h, Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		g.drawString(title, x + 5, y + MINIMIZED_HEIGHT / 2);
		g.drawRect(x, y, w, MINIMIZED_HEIGHT);
		clickArea.check(x, y, w, h);
		
		if (clickArea.hasClick() && !animator.animationExists("stretch")) {
			setMinimized(!isMinimized);
		}
		
	}
	
	@Override
	public void update() {
		animator.animate();
		
		if (!animator.animationExists("stretch") && isMinimized) {
			for (LabComponent c : getChildren()) {
				c.setVisible(false);
			}
		} else {
			for (LabComponent c : getChildren()) {
				c.setVisible(true);
			}
		}
	}
	
	@Override
	public void initJPanel(JPanel panel) {
		clickArea.initializeMouseListeners(panel);
	}

}
