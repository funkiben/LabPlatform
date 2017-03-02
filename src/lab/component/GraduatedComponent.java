package lab.component;

import lab.util.Graduation;

public abstract class GraduatedComponent extends MeasurableComponent {
	
	private Graduation graduation = null;
	
	public GraduatedComponent(int width, int height) {
		super(width, height);
	}

	public Graduation getGraduation() {
		return graduation;
	}

	public void setGraduation(Graduation graduation) {
		this.graduation = graduation;
	}

}
