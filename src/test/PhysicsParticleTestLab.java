package test;

import lab.LabFrame;
import lab.component.LabComponent;
import lab.component.geo.Line;
import lab.component.geo.Oval;
import lab.util.Vector2;

public class PhysicsParticleTestLab extends LabFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new PhysicsParticleTestLab();
	}
	
	
	private Vector2 position = new Vector2(400, 400);
	private Vector2 velocity = new Vector2(2, 4);
	private final Vector2 edgeStart = new Vector2(100, 500);
	private final Vector2 edgeEnd = new Vector2(600, 620);

	private final Oval particle = new Oval(20, 20);
	private final Line edge = new Line((int) edgeStart.getX(), (int) edgeStart.getY(), (int) edgeEnd.getX(),
			(int) edgeEnd.getY());

	public PhysicsParticleTestLab() {
		super("Physics Particle Test Lab", 800, 800);

		getRoot().setLayout(LabComponent.FREE_FORM);
		getRoot().setScaleChildren(false);

		addComponent(particle, edge);

		start(60);
		

	}

	@Override
	public void update() {
		particle.setOffsetX((int) position.getX());
		particle.setOffsetY((int) position.getY());

		Vector2 newPosition = position.add(velocity);
		
		Vector2 intersection = getLineIntersectionPoint(position, newPosition, edgeStart, edgeEnd);

		if (intersection != null) {
			
			double m = (edgeEnd.getY() - edgeStart.getY()) / (edgeEnd.getX() - edgeStart.getX());
			m = 1.0 / -m;
			
			Vector2 n = new Vector2(1, m);
			
			Vector2 u = n.multiply(velocity.dot(n) / n.dot(n));
			Vector2 w = velocity.subtract(u);
			
			velocity = w.subtract(u);
			
 
		}
		
		position = position.add(velocity);


	}

	private static Vector2 getLineIntersectionPoint(Vector2 v1, Vector2 v2, Vector2 v3, Vector2 v4) {

		double x1, y1, x2, y2, x3, y3, x4, y4;

		x1 = v1.getX();
		y1 = v1.getY();
		x2 = v2.getX();
		y2 = v2.getY();
		x3 = v3.getX();
		y3 = v3.getY();
		x4 = v4.getX();
		y4 = v4.getY();

		if (y1 == y2) {
			x1 += 0.001;
		}

		if (y3 == y4) {
			x3 += 0.001;
		}

		if (x1 == x2) {
			y1 += 0.001;
		}

		if (x3 == x4) {
			y3 += 0.001;
		}

		double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
		if (denom == 0.0) { // Lines are parallel.
			return null;
		}
		double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
		double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
		if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
			// Get the intersection point.
			return new Vector2(x1 + ua * (x2 - x1), y1 + ua * (y2 - y1));
		}

		return null;
	}

}
