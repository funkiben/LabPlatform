package lab.component.container;

import java.awt.Graphics;

public class BulbV2 extends Container {

	private static final int VERTICES = 31;
	private static final double OPENING_WIDTH = 0.025;
	private static final double OPENING_HEIGHT = 0.05;
	
	private static final double[] POLY1_X = new double[VERTICES + 3];
	private static final double[] POLY1_Y = new double[VERTICES + 3];
	private static final double[] POLY2_X = new double[VERTICES + 3];
	private static final double[] POLY2_Y = new double[VERTICES + 3];
	
	static {
		double angle, x, y;
		
		for (int i = 0; i < VERTICES; i++) {
			angle = (double) i / VERTICES * (Math.PI);
			
			x = Math.cos(angle) * 0.5;
			y = Math.sin(angle) * (0.5 - OPENING_HEIGHT);
			
			POLY1_X[i] = x + 0.5;
			POLY1_Y[i] = y + (0.5 - OPENING_HEIGHT);
			
			POLY2_X[i] = -x + 0.5;
			POLY2_Y[i] = -y + (0.5 - OPENING_HEIGHT);
		}
		
		POLY1_X[VERTICES] = 1;
		POLY1_Y[VERTICES] = 1;
		
		POLY1_X[VERTICES + 1] = 1;
		POLY1_Y[VERTICES + 1] = 0;
		
		POLY1_X[VERTICES + 2] = 0.5 + OPENING_WIDTH / 2.0;
		POLY1_Y[VERTICES + 2] = 0;
		
		
		POLY2_X[VERTICES] = 0;
		POLY2_Y[VERTICES] = 1;
		
		POLY2_X[VERTICES + 1] = 0;
		POLY2_Y[VERTICES + 1] = 0;
		
		POLY2_X[VERTICES + 2] = 0.5 - OPENING_WIDTH / 2.0;
		POLY2_Y[VERTICES + 2] = 0;
	}
	
	public BulbV2(int width, int height) {
		super(width, height);
		
		
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		
		int[] polyX1 = new int[VERTICES + 3];
		int[] polyY1 = new int[VERTICES + 3];
		int[] polyX2 = new int[VERTICES + 3];
		int[] polyY2 = new int[VERTICES + 3];
		
		for (int i = 0; i < VERTICES + 3; i++) {
			polyX1[i] = (int) (POLY1_X[i] * width + x);
			polyY1[i] = (int) (POLY1_Y[i] * height + y);
			polyX2[i] = (int) (POLY2_X[i] * width + x);
			polyY2[i] = (int) (POLY2_Y[i] * height + y);
		}
		
		g.fillPolygon(polyX1, polyY1, VERTICES + 3);
		

	}

}
