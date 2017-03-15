package lab.component.sensor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.SwingConstants;

import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.geo.Rectangle;
import lab.component.swing.Label;
import lab.util.SigFig;

public class PressureGauge extends MeasurableComponent {

	static {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
					PressureGauge.class.getResourceAsStream("/DSEG14Classic-Regular.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private final Label titleLabel;
	private final Label gaugeLabel;
	private int sigfigs;
	private int minPowerForScientificNotation;
	private String units;
	private Font font = new Font("DSEG14 Classic", Font.PLAIN, 13);
	
	public PressureGauge(int width, int height, String title, String units, int sigfigs) {
		this(width, height, title, units, sigfigs, 1);
	}

	public PressureGauge(int width, int height, String title, String units, int sigfigs, int minPowerForScientificNotation) {
		super(width, height);
		
		this.units = units;
		this.minPowerForScientificNotation = minPowerForScientificNotation;
		
		setLayout(LabComponent.FREE_FORM);

		this.sigfigs = sigfigs;

		gaugeLabel = new Label((int) (width / 1.5), height / 6);
		gaugeLabel.setOffset((width - gaugeLabel.getWidth()) / 2, 2 * (height - gaugeLabel.getHeight()) / 3);
		gaugeLabel.setShowBounds(true);
		Rectangle gaugeBorder = new Rectangle(gaugeLabel.getWidth() + 10, gaugeLabel.getHeight() + 10);
		gaugeLabel.addChild(gaugeBorder);
		gaugeBorder.setFillColor(Color.black);
		gaugeBorder.setOffset(-5, -3);
		gaugeLabel.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		gaugeLabel.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		gaugeLabel.setWrap(true);
		gaugeLabel.setColor(Color.green);
		
		gaugeLabel.setFont(font);

		titleLabel = new Label(width / 2, height / 6, "<center>" + title + "</center>");
		titleLabel.setOffset((width - titleLabel.getWidth()) / 2, 5 * (width - titleLabel.getWidth()) / 12);
		titleLabel.setShowBounds(true);
		Rectangle titleBorder = new Rectangle(titleLabel.getWidth() + 5, titleLabel.getHeight() + 5);
		titleLabel.addChild(titleBorder);
		titleBorder.setFillColor(Color.black);
		titleBorder.setOffset(-3, -1);
		titleLabel.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.getJComponent().setFont(titleLabel.getJComponent().getFont().deriveFont(10f));
		titleLabel.setWrap(true);
		titleLabel.setFont(font.deriveFont(12.0f));
		titleLabel.setColor(Color.lightGray);

		addChild(titleLabel);
		addChild(gaugeLabel);

	}

	public int getSigfigs() {
		return sigfigs;
	}

	public void setSigfigs(int sigfigs) {
		this.sigfigs = sigfigs;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getTitleText() {
		return titleLabel.getText().replace("<center>", "").replaceAll("</center>", "");
	}
	
	public void setTitleText(String title) {
		titleLabel.setText("<center>" + title + "</center>");
	}
	
	public int getMinPowerForScientificNotation() {
		return minPowerForScientificNotation;
	}

	public void setMinPowerForScientificNotation(int minPowerForScientificNotation) {
		this.minPowerForScientificNotation = minPowerForScientificNotation;
	}
	
	public Label getGaugeLabel() {
		return gaugeLabel;
	}
	
	public Label getTitleLabel() {
		return titleLabel;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public void setValue(double value) {
		super.setValue(value);
		
		gaugeLabel.setText(SigFig.sigfigalize(value, sigfigs, minPowerForScientificNotation) + " " + units);

	}
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, width, height);

		int maxValue = 200;
		int maxRuns = 10;

		for (int i = 0; i < maxRuns; i++) {
			g.setColor(new Color((maxValue / maxRuns) * i, (maxValue / maxRuns) * i, (maxValue / maxRuns) * i));
			g.fillOval(x + i, y + i, width - 2 * i, height - 2 * i);
		}
		
		/*
		gaugeLabel.getJComponent().setFont(gaugeLabel.getJComponent().getFont()
				.deriveFont((float) 1.1 * sigfigs * (float) (Math.sqrt(width * width + height * height)
						/ Math.sqrt(gaugeLabel.getWidth() * gaugeLabel.getWidth() + gaugeLabel.getHeight() * gaugeLabel.getWidth()))));
		 */
	}

}
