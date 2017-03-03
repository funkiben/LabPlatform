package lab.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageComponent extends LabComponent{

	private BufferedImage img;
	public ImageComponent(int width, int height, String fileName) {
		super(width, height);
		img = null;
		try {
		    img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.drawImage(img, width, height, null);
	}

}
