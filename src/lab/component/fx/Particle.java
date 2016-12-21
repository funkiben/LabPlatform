package lab.component.fx;

import java.awt.Color;
import java.awt.Graphics;

import draw.animation.Animator;
import draw.animation.ColorLinearAnimation;
import lab.Vector2;

public class Particle {
	
	public static final int RECTANGLE = 0;
	public static final int ELLIPSE = 1;
	
	private final ParticleSystem system;
	private final Animator animator = new Animator();
	
	private boolean active = false;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private double width;
	private double widthChange;
	private double height;
	private double heightChange;
	private double speedChange;
	private int lifetime;
	private int life;
	private Color color;
	private int shape;
	private Color[] colors;
	
	public Particle(ParticleSystem system) {
		this.system = system;
	}
	
	void start() {
		position = system.getSpawnArea().getRandomVector2();
		velocity = system.getVelocity().getRandomVector2();
		acceleration = system.getAcceleration().getRandomVector2();
		width = system.getParticleWidth().getRandomDouble();
		widthChange = system.getParticleWidthChange().getRandomDouble();
		height = system.getParticleHeight().getRandomDouble();
		heightChange = system.getParticleHeightChange().getRandomDouble();
		speedChange = system.getSpeedChange().getRandomDouble();
		lifetime = (int) system.getLifetime().getRandomDouble();
		color = system.getColor().getRandomColor();
		shape = system.getShape();
		colors = system.getColors();
		
		float colorFade = (float) system.getColorFade().getRandomDouble();

		life = 0;
		
		active = true;
	
		animator.addAnimation("color", new ColorLinearAnimation(colors, colorFade) {
			
			@Override
			public void setValue(Color c) {
				color = c;
			}
			
			@Override
			public Color getValue() {
				return color;
			}
			
		});
		
		
	}
	
	boolean isActive() {
		return active;
	}
	
	void stop() {
		this.active = false;
	}
	
	void update() {
		if (!active) {
			return;
		}
		
		life++;
		
		if (life >= lifetime) {
			active = false;
			return;
		}
		
		position = position.add(velocity);
		velocity = velocity.add(acceleration);
		
		if (speedChange != 0) {
			velocity = velocity.extend(speedChange);
		}
		
		width += widthChange;
		height += heightChange;
		
		animator.animate();
		
	}
	
	void draw(Graphics g, int x, int y, int w, int h) {
		if (!active) {
			return;
		}
		
		double dx = position.getX() / system.getWidth() * w + x;
		double dy = position.getY() / system.getHeight() * h + y;
		double dw = width / system.getWidth() * w;
		double dh = height / system.getHeight() * h;
		
		
		g.setColor(color);
		
		if (shape == ELLIPSE) {
			g.fillOval((int) dx - (int) (dw / 2), (int) dy - (int) (dh / 2), (int) dw, (int) dh);
		} else if (shape == RECTANGLE) {
			g.fillOval((int) dx - (int) (dw / 2), (int) dy - (int) (dh / 2), (int) dw, (int) dh);
		}
		
	}

}
