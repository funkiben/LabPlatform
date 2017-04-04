package lab.util;

public class Vector2 {

	private double x;
	private double y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void set(Vector2 other) {
		x = other.x;
		y = other.y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 add(double x, double y) {
		return new Vector2(this.x + x, this.y + y);
	}
	
	public Vector2 add(Vector2 other) {
		return add(other.getX(), other.getY());
	}
	
	public Vector2 subtract(double x, double y) {
		return add(-x, -y);
	}
	
	public Vector2 subtract(Vector2 other) {
		return subtract(other.getX(), other.getY());
	}
	
	public Vector2 multiply(double m) {
		return new Vector2(x * m, y * m);
	}
	
	public Vector2 divide(double d) {
		return multiply(1 / d);
	}
	
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	
	public Vector2 rotate(double deg) {
		deg = Math.toRadians(deg);
		double s = Math.sin(deg);
		double c = Math.cos(deg);
		return new Vector2(x * c - y * s, x * s + y * c);
	}
	
	public double angleBetween(Vector2 other) {
		return Math.atan2(y - other.getY(), x - other.getX()) * 180.0 / Math.PI;
	}
	
	public double angle() {
		return Math.atan2(y, x) * 180.0 / Math.PI;
	}
	
	public double distanceSqrt(Vector2 other) {
		double dx = x - other.getX(), dy = y = other.getY();
		
		return dx * dx + dy * dy;
	}
	
	public double distance(Vector2 other) {
		return Math.sqrt(distanceSqrt(other));
	}
	
	public double length() {
		return Math.sqrt(x * x + y * y);
	}
	
	public double lengthSqrt() {
		return x * x + y * y;
	}
	
	public Vector2 normalize(double length) {
		double l = fastInverseSqrt(x * x + y * y);
		return new Vector2((x * l) * length, (y * l) * length);
	}
	
	public Vector2 normalize() {
		return normalize(1.0);
	}
	
	public Vector2 extend(double amount) {
		return normalize(length() + amount);
	}
	
	public double dot(Vector2 other) {
		return x * other.x + y * other.y;
	}
	
	public double cross(Vector2 other) {
		return x * other.y - y * other.x;
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
	
	public Vector2 clone() {
		return new Vector2(x, y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vector2)) {
			return false;
		}
		Vector2 other = (Vector2) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}

	public static Vector2 parse(String str) {
		String[] nums = str.split(",");
		
		double x = Double.parseDouble(nums[0]);
		double y = Double.parseDouble(nums[1]);
		
		return new Vector2(x, y);
		
	}
	
	private static float fastInverseSqrt(float x) {
	    float xhalf = 0.5f * x;
	    int i = Float.floatToIntBits(x);
	    i = 0x5f3759df - (i >> 1);
	    x = Float.intBitsToFloat(i);
	    x *= (1.5f - xhalf * x * x);
	    return x;
	}
	
	private static double fastInverseSqrt(double x) {
	    double xhalf = 0.5d * x;
	    long i = Double.doubleToLongBits(x);
	    i = 0x5fe6ec85e7de30daL - (i >> 1);
	    x = Double.longBitsToDouble(i);
	    x *= (1.5d - xhalf * x * x);
	    return x;
	}
	
	
	
	

}
