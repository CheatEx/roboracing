package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class Robot extends WorldObject implements Speed,Serializable {
	
	private String name;
	
	private Team team;
	
	private double vx;
	
	private double vy;
	
	public static final double RADIUS = 3.0;

	public Robot(String name, Team team, Coordinate c) {
		//XXX robot's radius magic there!!!
		super(c.getX(), c.getY(), RADIUS);
		this.name = name;
		this.team = team; 
	}
	
	public Robot(Robot r) {
		this(r.getName(), r.getTeam(), new Coordinate(r.getX(), r.getY()));
		vx = r.getVx();
		vy = r.getVy();
	}

	/**
	 * Name of robot. Must be unique on game land.
	 * @return robot's name
	 */
	public String getName() {
		return name;
	}

	public Team getTeam() {
		return team;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	@Override
	public void setRadius(double radius) {
		throw new UnsupportedOperationException("Radius change not allowed for Robot");
	}
}
