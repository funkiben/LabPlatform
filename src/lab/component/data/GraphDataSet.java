package lab.component.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lab.util.Vector2;

public class GraphDataSet {
	
	private String name;
	private boolean connectPoints;
	private boolean showName;
	private Color color = Color.red;
	private List<Vector2> points = new ArrayList<Vector2>();
	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName) {
		this.name = name;
		this.connectPoints = connectPoints;
		this.showName = showName;
	}
 	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName, List<Vector2> points) {
		this(name, connectPoints, showName);
		
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConnectPoints() {
		return connectPoints;
	}

	public void setConnectPoints(boolean connectPoints) {
		this.connectPoints = connectPoints;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Vector2> getPoints() {
		return points;
	}

	public void setPoints(List<Vector2> points) {
		this.points = points;
	}
	
	public void addPoint(Vector2 point) {
		points.add(point);
	}
	
	public void addPoint(double x, double y) {
		points.add(new Vector2(x, y));
	}
	
	public void clearPoints() {
		points.clear();
	}


	

}
