package lab.component;

public abstract class MeasurableComponent extends LabComponent {
	
	private double value = 0.0D;
	private boolean showValue = false;
	
	public MeasurableComponent(int width, int height) {
		super(width, height);
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public void setShowValue(boolean showValue) {
		this.showValue = showValue;
	}
	
	public boolean canShowValue() {
		return showValue;
	}
	
}
