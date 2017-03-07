package lab.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageComponent extends LabComponent {

	private BufferedImage img;

	public ImageComponent(int width, int height, String fileName) {
		super(width, height);
		readImage(fileName);
	}

	public ImageComponent(String fileName) {
		super(0, 0);
		readImage(fileName);
		if(img != null){
			setWidth(img.getWidth());
			setHeight(img.getHeight());
		}
	}
	
	private void readImage(String fileName){
		try {
			InputStream is = new BufferedInputStream(ImageComponent.class.getResourceAsStream(fileName));
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

}
