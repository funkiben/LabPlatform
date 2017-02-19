package lab.component.swing.input;

import java.awt.Color;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DoubleSlider extends InputComponent implements ChangeListener {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;

	private final JSlider slider;
	private double min;
	private double max;
	private double increment;

	public DoubleSlider(int width, double min, double max, double increment, int orientation) {
		super(width, 20);

		this.min = min;
		this.max = max;
		this.increment = increment;
		
		slider = new JSlider();
		slider.setMinimum(0);
		setMaxSliderValue();
		slider.setValue(0);
		slider.setOrientation(orientation);
		slider.setBackground(Color.white);
		slider.addChangeListener(this);

	}

	public void setValue(Double v) {
		v = Math.max(min, Math.min(max, v));
		v -= min;
		slider.setValue((int) (v / increment));
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
		setMaxSliderValue();
	}

	public void setMin(double min) {
		this.min = min;
		setMaxSliderValue();
	}

	public void setMax(double max) {
		this.max = max;
		setMaxSliderValue();
	}
	
	private void setMaxSliderValue() {
		slider.setMaximum((int) ((max - min) / increment));
	}

	@Override
	public Double getValue() {
		return slider.getValue() * increment + min;
	}

	@Override
	public void setValue(Object v) {
		setValue((Double) v);
	}

	public int getOrientation() {
		return slider.getOrientation();
	}

	@SuppressWarnings("unchecked")
	public void addValueLabel(double value, String label) {
		Dictionary<Integer, JLabel> labels = slider.getLabelTable();

		if (labels == null) {
			labels = new Hashtable<Integer, JLabel>();
		}

		labels.put(new Integer((int) ((value - min) / increment)), new JLabel(label));

		slider.setLabelTable(labels);

		slider.setPaintLabels(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		onChange();
	}

	public void onChange() {

	}

	@Override
	public JSlider getJComponent() {
		return slider;
	}

}
