package lab.component.input;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import lab.component.LabComponent;

public abstract class InputComponent extends LabComponent {

	private boolean enabled = true;
	private boolean refreshJComponent = true;
	
	public InputComponent(int width, int height) {
		super(width, height);
		
		setOffsetX(10);
		setOffsetY(10);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		getJComponent().setEnabled(enabled);
	}
	
	public void refreshJComponent() {
		refreshJComponent = true;
	}
	
	@Override
	public void draw(final int x, final int y, final int width, final int height, Graphics g) {
		
		if (getLastDrawWidth() != width || getLastDrawHeight() != height || getLastDrawX() != x || getLastDrawY() != y) {
			refreshJComponent = true;
		}
		
		if (refreshJComponent) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					getJComponent().setLocation(x, y);
					getJComponent().setSize(width, height);
					getJComponent().setEnabled(enabled);
					getJComponent().doLayout();
					//LabFrame.inst.getDrawCanvas().repaint();
				}
			});
			
			refreshJComponent = false;
		}
	}
	
	public void initJPanel(JPanel panel) {
		panel.add(getJComponent());
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		getJComponent().setVisible(visible);
	}
	
	public abstract Component getJComponent();
}
