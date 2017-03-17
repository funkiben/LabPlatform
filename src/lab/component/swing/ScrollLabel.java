package lab.component.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import lab.component.swing.input.FontStyle;

public class ScrollLabel extends SwingComponent {

	public static final int HORIZONTAL_SCROLLBAR_ALWAYS = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
	public static final int HORIZONTAL_SCROLLBAR_NEVER = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
	public static final int HORIZONTAL_SCROLLBAR_AS_NEEDED = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

	private final JLabel label;
	private final JScrollPane scrollPane;

	public ScrollLabel(int width, int height, String filePath) {
		super(width, height);
		label = new JLabel(readFile(filePath));
		scrollPane = new JScrollPane(label);
	}

	private static String readFile(String path) {
		BufferedReader bufferedReader;

		try {
			InputStream inputStream = ScrollLabel.class.getResourceAsStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();

			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			return stringBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Component getJComponent() {
		return scrollPane;
	}

	public void setText(String text) {
		label.setText(text);
	}

	public void setHoriztonalScrollBarPolicy(int policy) {
		scrollPane.setHorizontalScrollBarPolicy(policy);
	}

	public void setFontSize(float size) {
		label.setFont(label.getFont().deriveFont(size));
	}

	public int getFontSize() {
		return label.getFont().getSize();
	}

	public void setFontStyle(FontStyle style) {
		label.setFont(label.getFont().deriveFont(style.ordinal()));
	}

	public FontStyle getFontStyle() {
		return FontStyle.values()[label.getFont().getStyle()];
	}

	public void setFont(Font font) {
		label.setFont(font);
	}

	public Font getFont() {
		return label.getFont();
	}

	public void setColor(Color color) {
		label.setForeground(color);
	}

	public Color getColor() {
		return label.getForeground();
	}

}
