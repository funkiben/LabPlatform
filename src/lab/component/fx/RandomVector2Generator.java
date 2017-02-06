package lab.component.fx;

import lab.Vector2;

public class RandomVector2Generator {
	
	public static final int ELLIPSE = 0;
	public static final int RECTANGLE = 1;
	public static final int RANDOM_DIRECTION = 2;
	
	private Vector2 start;
	private Vector2 end;
	private int type;
	
	public RandomVector2Generator(Vector2 start, Vector2 end, int type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}
	
	public RandomVector2Generator(Vector2 constant) {
		this(constant, constant, RECTANGLE);
	}
	
	public RandomVector2Generator(double startX, double startY, double endX, double endY, int type) {
		this(new Vector2(startX, startY), new Vector2(endX, endY), type);
	}
	
	public RandomVector2Generator(double width, double height, int type) {
		this(0, 0, width, height, type);
	}
	
	public RandomVector2Generator(double magnitude) {
		this(0, magnitude, 0, 0, RANDOM_DIRECTION);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public double getRangeX() {
		return end.getX() - start.getX();
	}
	
	public double getRangeY() {
		return end.getY() - start.getY(); 
	}
	
	public Vector2 getStart() {
		return start;
	}

	public void setStart(Vector2 start) {
		this.start = start;
	}

	public Vector2 getEnd() {
		return end;
	}

	public void setEnd(Vector2 end) {
		this.end = end;
	}
	
	public boolean noRange() {
		return getRangeX() == 0 && getRangeY() == 0;
	}
	
	public Vector2 getRandomVector2() {
		
		if (noRange()) {
			return start;
		}
		
		double x, y;
		
		if (type == RECTANGLE) {
			
			x = Math.random() * getRangeX() + start.getX();
			y = Math.random() * getRangeY() + start.getY();
			
			return new Vector2(x, y);
			
		} else if (type == ELLIPSE) {
			
			double theta, w2, h2, radiusSqrt;
			
			theta = Math.random() * Math.PI * 2;
			w2 = getRangeX() / 2;
			h2 = getRangeY() / 2;
			
			radiusSqrt = Math.sqrt(Math.random());
			
			x = radiusSqrt * Math.cos(theta) * w2 + (start.getX() + end.getX()) / 2;
			y = radiusSqrt * Math.sin(theta) * h2 + (start.getY() + end.getY()) / 2;
				
			return new Vector2(x, y);
			
		} else if (type == RANDOM_DIRECTION) {
			
			return start.rotate(Math.random() * 360);
			
		}
		
		return null;
	}
	

}
