package lab.component.sensor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.SwingConstants;

import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.geo.Rectangle;
import lab.component.swing.Label;
import lab.substance.SubstanceData;
import lab.util.SigFig;

public class PressureGauge extends MeasurableComponent {

	private Label titleText;
	private int sigfigs;
	private Label gauge;
	private String units;

	public PressureGauge(int width, int height, String title, String units, int sigfigs) {
		super(width, height);
		setValue(0);
		this.units = units;
		setLayout(LabComponent.FREE_FORM);

		this.sigfigs = sigfigs;

		gauge = new Label(width / 2, height / 4);
		gauge.setOffset((width - gauge.getWidth()) / 2, 2 * (height - gauge.getHeight()) / 3);
		gauge.setShowBounds(true);
		Rectangle gaugeBorder = new Rectangle(gauge.getWidth() + 10, gauge.getHeight() + 10);
		gauge.addChild(gaugeBorder);
		gaugeBorder.setFillColor(Color.white);
		gaugeBorder.setOffset(-5, -3);
		gauge.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		gauge.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		gauge.setWrap(true);

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(
					Font.createFont(Font.TRUETYPE_FONT, PressureGauge.class.getResourceAsStream("/DSEG14Classic-Regular.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		Font sevenSegment = new Font("DSEG14 Classic", Font.PLAIN, 12);
		gauge.setFont(sevenSegment);

		titleText = new Label(width / 2, height / 6, "<center>" + title + "</center>");
		titleText.setOffset((width - titleText.getWidth()) / 2, 5 * (width - titleText.getWidth()) / 12);
		titleText.setShowBounds(true);
		Rectangle titleBorder = new Rectangle(titleText.getWidth() + 5, titleText.getHeight() + 5);
		titleText.addChild(titleBorder);
		titleBorder.setFillColor(Color.white);
		titleBorder.setOffset(-3, -1);
		titleText.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		titleText.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		titleText.getJComponent().setFont(titleText.getJComponent().getFont().deriveFont(10f));
		titleText.setWrap(true);
		titleText.setFont(sevenSegment);

		addChild(titleText);
		addChild(gauge);

	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		this.setValue(this.getValue() + .001);

		gauge.setText(SigFig.sigfigalize(this.getValue(), sigfigs) + " " + units);

		g.setColor(Color.black);
		g.fillOval(x, y, width, height);

		int maxValue = 200;
		int maxRuns = 10;

		for (int i = 0; i < maxRuns; i++) {
			g.setColor(new Color((maxValue / maxRuns) * i, (maxValue / maxRuns) * i, (maxValue / maxRuns) * i));
			g.fillOval(x + i, y + i, width - 2 * i, height - 2 * i);
		}

		gauge.getJComponent().setFont(gauge.getJComponent().getFont()
				.deriveFont((float) 1.1 * sigfigs * (float) (Math.sqrt(width * width + height * height)
						/ Math.sqrt(gauge.getWidth() * gauge.getWidth() + gauge.getHeight() * gauge.getWidth()))));

	}

}
