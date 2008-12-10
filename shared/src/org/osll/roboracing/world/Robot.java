package org.osll.roboracing.world;

/**
 * @author zan
 */
public class Robot extends WorldObject implements Speed {
	
	private String name;
	
	private Team team;
	
	private double vx;
	
	private double vy;

	public Robot(String name, Team team) {
		this.name = name;
		this.team = team; 
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
}
