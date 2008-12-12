package org.osll.roboracing.server.game;

import static java.lang.Math.sqrt;

import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Speed;

public final class Utils {

	public static double distance(Coordinate first, Coordinate second) {
		return module(first.getX() - second.getX(), first.getY() - second.getY());
	}
	
	public static double module(Speed speed) {
		return module(speed.getVx(), speed.getVy());
	}
	
	public static double module(double x, double y) {
		return sqrt(y*y + x*x);
	}
}
