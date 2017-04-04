package lab.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

//creates image component from an image file
public class ImageComponent extends LabComponent {

	private final BufferedImage img;

	// create image component with custom width and height
	public ImageComponent(int width, int height, String fileName) {
		super(width, height);
		img = readImage(fileName);
	}

	// create image component with default width and height
	public ImageComponent(String fileName) {
		super(0, 0);
		img = readImage(fileName);
		if (img != null) {
			setWidth(img.getWidth());
			setHeight(img.getHeight());
		}
	}

	// try to read the image
	private static BufferedImage readImage(String fileName) {
		try {
			InputStream is = new BufferedInputStream(ImageComponent.class.getResourceAsStream(fileName));
			return ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BufferedImage getImage() {
		return img;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

}
