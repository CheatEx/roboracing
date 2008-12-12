package org.osll.roboracing.server.game.engine;

import java.util.HashMap;
import java.util.Map;

import org.osll.roboracing.world.Coordinate;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.Team;

public class InitialPlacingStrategy {

	private double angularStep;
	
	private double placeRadius;
	
	private Map<Team, Integer> teams = new HashMap<Team, Integer>();
	
	public InitialPlacingStrategy(double worldRadius) {
		for (Team team : Team.values())
			teams.put(team, 0);
		
		placeRadius = worldRadius - Robot.RADIUS;
		angularStep = 2*Math.PI*(placeRadius)/(Robot.RADIUS*2.2);
	}

	public Coordinate placeNew(Team team) {
		int count = teams.get(team);
		count++;
		//rewrite to support unlimited teams
		double groupCenter =
			team == Team.Interceptors ? 0 : Math.PI;
		double angle = groupCenter + angularStep*count;
		teams.put(team, count);
		
		return new Coordinate(
				placeRadius*Math.cos(angle),
				placeRadius*Math.sin(angle));
	}
}
