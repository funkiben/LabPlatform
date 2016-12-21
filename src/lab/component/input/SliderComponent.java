package lab.component.input;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderComponent extends InputComponent implements ChangeListener {

	private final JSlider slider;
	private final JLabel valueField;
	private String maximumValue;
	private boolean showValue;
	private double minValue;
	private double maxValue;

	public SliderComponent(int width, int height, double min, double max, boolean showValueField) {
		
		super(width, height);
		
		slider = new JSlider((int) (min + .5), (int) (max + .5));
		valueField = new JLabel();
		showValue = showValueField;
		maximumValue = "" + max;
		minValue = min;
		maxValue = max;
	}

	public void setValue(double v) {
		if(v < minValue) {
			slider.setValue((int) minValue);
		} if (v > maxValue) {
			slider.setValue((int) maxValue);
		} else {
			slider.setValue((int) v);
		}
	}
	
	public JLabel getValueField() {
		return valueField;
	}

	public JSlider getSlider() {
		return slider;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {

	}

	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {

		
		FontMetrics fontMetrics = panel.getFontMetrics(panel.getFont());
		
		if (showValue) {
			
			panel.add(slider);
			
			slider.setSize(width - fontMetrics.stringWidth(maximumValue), height);
			slider.setLocation(x, y);
			slider.setEnabled(this.isActivated());
			slider.setBackground(Color.white);
			
			
			panel.add(valueField);
			
			valueField.setSize(fontMetrics.stringWidth(maximumValue), height);
			
			valueField.setLocation(x + width - fontMetrics.stringWidth(maximumValue), y);
			valueField.setVisible(true);
			valueField.setText(slider.getValue() + "");
			slider.addChangeListener(this);
			
		} else {
			
			panel.add(slider);
			
			slider.addChangeListener(this);
			slider.setSize(width, height);
			slider.setBackground(Color.white);
			slider.setLocation(x, y);
			slider.setEnabled(this.isActivated());
			
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		valueField.setText(slider.getValue() + "");
		onChange();
	}
	
	public void onChange() {
		
	}

	@Override
	public Component getJComponent() {
		return slider;
	}

	public int getValue() {
		return slider.getValue();
	}
	
}
