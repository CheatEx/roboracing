package org.osll.roboracing.server.game;

import static java.lang.Math.sqrt;

import org.osll.roboracing.world.Coordinate;

public final class Utils {

	public static double distance(Coordinate first, Coordinate second) {
		return sqrt(sqr(first.getX() - second.getX()) +  sqr(first.getY() - second.getY()));
	}
	
	public static double sqr(double x) {
		return x*x;
	}
}
