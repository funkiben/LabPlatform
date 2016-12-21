package lab.component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Piston extends GraduatedComponent implements MouseMotionListener, MouseListener {

	private Color gasColor = Color.red;
	private double gasMass = 1.0;
	private double transparencyModifier = 1.0;
	
	public Piston(int width, int height) {
		super(width, height);

		setGraduation(new VerticalGraduation(0, 25, 5, 1));
		getGraduation().setSuffix("mL");
		//getGraduation().setColor(Color.white);
		
		setOffsetX(30);
		setOffsetY(30);
		
		setValue(0);
		
		this.setShowValue(true);
	}
	
	public Color getGasColor() {
		return gasColor;
	}

	public void setGasColor(Color gasColor) {
		this.gasColor = gasColor;
	}

	public double getGasMass() {
		return gasMass;
	}

	public void setGasMass(double gasMass) {
		this.gasMass = gasMass;
	}
	
	public double getTransparencyModifier() {
		return transparencyModifier;
	}

	public void setTransparencyModifier(double transparencyModifier) {
		this.transparencyModifier = transparencyModifier;
	}
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		List<Point> edge = new ArrayList<Point>();
		edge.add(new Point(x + width, y + height));
		edge.add(new Point(x + width, y));
		getGraduation().setEdge(edge);
		
		double range = getGraduation().getEnd() - getGraduation().getStart();
		
		double pistonY = (range - getValue()) / range * height;
		double pistonHeight = getGraduation().getSubLineIntervals() / range * height;
		
		g.setColor(Color.lightGray);
		g.fillRect(x, y, width, height);
		
		double transparency = pistonY / height * transparencyModifier *  255;
		
		transparency = Math.max(transparency, 0);
		transparency = Math.min(transparency, 255);
		
		g.setColor(new Color(gasColor.getRed(), gasColor.getGreen(), gasColor.getBlue(), (int) (transparency)));
		g.fillRect(x, (int) (y + pistonY), width + 1, (int) (height - pistonY) + 1);
		
		g.setColor(Color.darkGray);
		g.fillRect(x - 5, y - (int) pistonHeight, 5, height + (int) pistonHeight);
		g.fillRect(x - 5, y + height, width + 10, 5);
		g.fillRect(x + width, y - (int) pistonHeight, 5, height + (int) pistonHeight);
		
		
		
		g.setColor(Color.darkGray);
		g.fillRect(x, (int) (pistonY + y - pistonHeight), width + 1, (int) pistonHeight + 1);
		
		
		if (getGraduation() != null) {
			getGraduation().draw(g, width, height);
		}
		
		if (click != null) {
			double mx = click.getX(), my = click.getY();

			if (my > y + pistonY - pistonHeight && my < y + pistonY && mx > x && mx < x + width) {
				
				mouseCoord = click;
				
				drag = true;
				click = null;
				
				
			}
		}
		
		
		if (drag) {
			setValue(range - (double) (mouseCoord.getY() - y) / height * range);
		}
		

		if (getValue() > range) {
			setValue(range);
		}
		
		if (getValue() < 0) {
			setValue(0);
		}
		
		
		if (canShowValue()) {
			g.setColor(Color.black);
			g.drawString((float) getValue() + "mL", x, y + height + 20);
		}

	}
	
	@Override
	public void drawInputs(int x, int y, int width, int height, JPanel panel) {
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	private Point mouseCoord = new Point(0, 0);
	private Point click = null;
	private boolean drag = false;
	
	@Override
	public void mouseReleased(MouseEvent e) {
		drag = false;
		click = null;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseCoord = e.getPoint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		click = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }
	
	@Override
	public void mouseMoved(MouseEvent e) { }
	
	

}
