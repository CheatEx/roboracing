package org.osll.roboracing.server.game.controller;

import static org.osll.roboracing.server.game.Utils.distance; 

import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.WorldObject;

public class VisionRadiusFilter implements ObjectFilter {

	private Coordinate center;
	
	private double radius;
	
	public VisionRadiusFilter(Coordinate center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	@Override
	public boolean objectAccepted(WorldObject object) {
		return distance(center, object) - object.getRadius() <= radius;
	}

}
