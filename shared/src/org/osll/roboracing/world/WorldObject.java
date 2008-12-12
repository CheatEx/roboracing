package org.osll.roboracing.world;

/**
 * @author zan
 */
public class WorldObject extends Coordinate {

	private double radius;
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public WorldObject(double x, double y, double r) {
		super(x, y);
		radius = r;
	}

	public WorldObject(WorldObject obj) {
		this(obj.getX(), obj.getY(), obj.getRadius());
	}
}