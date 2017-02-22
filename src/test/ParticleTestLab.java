package test;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import lab.LabFrame;
import lab.Vector2;
import lab.component.fx.Particle;
import lab.component.fx.ParticleShape;
import lab.component.fx.ParticleSystem;
import lab.component.fx.RandomVector2Generator;
import lab.component.fx.Vector2DistributionType;
import lab.component.geo.Line;

public class ParticleTestLab extends LabFrame implements MouseMotionListener {
	
	private static final long serialVersionUID = 5498968999145696519L;

	public static void main(String[] args) {
		new ParticleTestLab();
	}
	
	private final ParticleSystem particles;
	
	public ParticleTestLab() {
		super("Particle Test Lab", 800, 800);
		
		particles = new ParticleSystem(800, 800, 1000);
		
		particles.setColor(new Color(0, 0, 0, 255));
		particles.setColors(new Color(100, 100, 100, 100), new Color(0, 0, 0, 0));
		
		particles.setColorFade(5);
		
		particles.setVelocity(new RandomVector2Generator(-2, -3, 2, -5, Vector2DistributionType.ELLIPSE));
		particles.setSpeedChange(-0.025);
		
		particles.setParticleWidth(5);
		particles.setParticleHeight(5);
		
		particles.setParticleWidthChange(0.5);
		particles.setParticleHeightChange(0.5);
		
		particles.setParticleSpawnRate(0.1);
		
		particles.setShape(ParticleShape.ELLIPSE);
		
		particles.setFriction(0.5);
		
		Vector2 v1 = new Vector2(100, 0).add(400, 400);
		Vector2 v2;
		for (int i = 0; i <= 51; i++) {
			double deg = i / 51.0 * 360.0;
			v2 = new Vector2(100, 0).rotate(deg).add(400, 400);
			particles.addCollidableEdge(v1, v2);
			addComponent(new Line((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY()));
			v1 = v2;
		}
		
		v1 = new Vector2(0, 275);
		for (int i = 0; i <= 100; i++) {
			v2 = new Vector2(i * (1.0 / 100.0 * 800.0), Math.sin(i / 10.0) * 50).add(0, 275);
			particles.addCollidableEdge(v1, v2);
			addComponent(new Line((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY()));
			v1 = v2;
		}
		
		particles.start();
		
		addComponent(particles);
		
		start(60);
		
		addMouseMotionListener(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) { }

	@Override
	public void mouseMoved(MouseEvent e) {
		particles.setSpawnArea(new Vector2(e.getX(), e.getY()));
	}


}
