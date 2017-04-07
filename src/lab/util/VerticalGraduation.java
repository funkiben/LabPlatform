package lab.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class VerticalGraduation extends Graduation implements Drawable {
	
	public VerticalGraduation(double start, double end, double lineIntervals, double subLineIntervals) {
		super(start, end, lineIntervals, subLineIntervals);
	}
	
	public VerticalGraduation(double end, double lineIntervals, double subLineIntervals) {
		super(0, end, lineIntervals, subLineIntervals);
	}
	
	public VerticalGraduation(Graduation g) {
		super(g);
	}

	@Override
	public void draw(int x, int y, int w, int h, Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, this.fontSize));
		FontMetrics metrics = g.getFontMetrics();
		float textHeight = metrics.getLineMetrics("0", g).getHeight();
		
		Point bottom, top, p;
		double height, range;
		
		bottom = edge.get(0);
		top = edge.get(edge.size() - 1);
		
		height = bottom.y - top.y; // top will have a lower y value than bottom (confusing)
		range = end - start;
		
		range = round(range);
		
		g.setColor(color);
		
		for (double i = 0; round(i) <= range; i += subLineIntervals) {
			
			p = findLinePosition(edge, (int) ((i / range * height) + top.y));
			p.y += offset;
			
			if (modulus(round(i), round(lineIntervals)) == 0.0) {
				g.drawLine(p.x, p.y, (int) (p.x - lineLength), p.y);
				
				if (showLabels) {
					String s = SigFig.sigfigalize(round(range - i + start), sigfigs, minPowerForScientificNotation) + (i == range ? suffix : "");
					
					if (removePointZero) {
						s = s.replace(".0", "");
					}
					
					g.drawString(s, p.x + 5 + textOffset, (int) (p.y + (textHeight / 2)));
				}
				
			} else {
				g.drawLine(p.x, p.y, (int) (p.x - subLineLength), p.y);
			}
			
		}
		
	}
	
	@Override
	public int getBottomTick() {
		return edge.get(0).y + offset;
	}
	
	public static Point findLinePosition(List<Point> edge, int y) {
		Point p1 = null, p2 = null;
		
		for (int i = 1; i < edge.size(); i++) {
			
			p1 = edge.get(i);
			p2 = edge.get(i - 1);
			
			if ((y <= p1.getY() && y >= p2.getY()) || (y >= p1.getY() && y <= p2.getY())) {
				break;
			}
			
		}
		
		// x = my + b
		// x - my = b
		
		double m, b;
		
		m = (p1.getX() - p2.getX()) / (p1.getY() - p2.getY());
		b = p1.getX() - m * p1.getY();
		
		return new Point((int) (y * m + b), y);
		
	}
	
}
