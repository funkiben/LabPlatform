package lab.component.input;

import java.awt.Component;

import lab.component.LabComponent;

public abstract class InputComponent extends LabComponent {

	private boolean activated = true;
	
	public InputComponent(int width, int height) {
		super(width, height);
		
		setOffsetX(10);
		setOffsetY(10);
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
		getJComponent().setEnabled(activated);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		getJComponent().setVisible(visible);
	}
	
	public abstract Component getJComponent();

}
