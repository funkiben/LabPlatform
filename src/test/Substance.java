package test;

import java.awt.Color;

import lab.component.container.ContentState;
import lab.component.data.GraphDataSet;
import lab.component.swing.LabelComponent;

public class Substance {
	private boolean isReactant;
	private double amount;
	private String name;
	private String units;
	
	private Color color;
	private ContentState state;
	private GraphDataSet set;
	private LabelComponent connectedLabel;
	
	private double heatOfVaporization;
	private double boilingPoint;
	private double boilPressure;
	
	public Substance(String name,double amount,boolean isReactant) {
		this.name = name;
		this.amount = amount;
		this.isReactant = isReactant;
	}
	
	public Substance(String name,double amount,boolean isReactant, Color color) {
		this(name,amount,isReactant);
		this.color = color;
	}
	
	public Substance(String name, double amount, boolean isReactant, String units) {
		this.name = name;
		this.amount = amount;
		this.isReactant = isReactant;
		this.units = units;
	}
	
	public void setBoilingConditions(double hov, double bpt, double bpressure) {
		this.heatOfVaporization = hov;
		this.boilingPoint = bpt;
		this.boilPressure = bpressure;
	}
	
	public boolean isReactant() {
		return isReactant;
	}

	public void setReactant(boolean isReactant) {
		this.isReactant = isReactant;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GraphDataSet getSet() {
		return set;
	}

	public void setSet(GraphDataSet dataSet) {
		set = new GraphDataSet(dataSet.getName(),dataSet.isConnectPoints(),dataSet.isShowName());
	}

	public LabelComponent getConnectedLabel() {
		return connectedLabel;
	}

	public void setConnectedLabel(LabelComponent connectedLabel) {
		this.connectedLabel = connectedLabel;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ContentState getState() {
		return state;
	}

	public void setState(ContentState state) {
		this.state = state;
	}

	public double getHeatOfVaporization() {
		return heatOfVaporization;
	}

	public void setHeatOfVaporization(double heatOfVaporization) {
		this.heatOfVaporization = heatOfVaporization;
	}

	public double getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(double boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public double getBoilPressure() {
		return boilPressure;
	}

	public void setBoilPressure(double boilPressure) {
		this.boilPressure = boilPressure;
	}

}
