package lab.component.swing.input;

import java.awt.Color;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IntegerSlider extends InputComponent implements ChangeListener {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	private final JSlider slider;
	private final int min;
	private final int max;
	
	public IntegerSlider(int width, int height, int min, int max, int orientation) {
		
		super(width, height);
		
		slider = new JSlider(min, max);
		slider.setValue(0);
		slider.setOrientation(orientation);
		slider.setBackground(Color.white);
		slider.addChangeListener(this);
		
		this.min = min;
		this.max = max;
		
	}
	
	public void setValue(int v) {
		v = Math.max(min, Math.min(max, v));
		slider.setValue(v);
	}
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
	@Override
	public Integer getValue() {
		return slider.getValue();
	}
	
	public void setValue(Object v) {
		setValue((Integer) v);
	}
	
	public int getOrientation() {
		return slider.getOrientation();
	}
	
	@SuppressWarnings("unchecked")
	public void addValueLabel(int value, String label) {
		Dictionary<Integer, JLabel> labels = slider.getLabelTable();
		
		if (labels == null) {
			labels = new Hashtable<Integer, JLabel>();
		}
		
		labels.put(value, new JLabel(label));
		
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