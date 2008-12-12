package org.osll.roboracing.server.game.engine;

import java.util.HashMap;

import org.osll.roboracing.server.game.Game;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Map;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;

/**
 * Game engine implementation. This class are not thread-safe!
 * @author zan
 */
public class GameImpl implements Game {

	private Map map;
	
	private InitialPlacingStrategy placer;
	
	private double time = 0;
	
	private HashMap<String, Robot> robots = new HashMap<String, Robot>();
	
	private PhysicalConstraints constraints;
	
	public GameImpl(PhysicalConstraints constraints) {
		this.constraints = constraints;
		placer = new InitialPlacingStrategy(constraints.getWorldRadius());
	}
	
	@Override
	public void addRobot(String name, Team team)
			throws IllegalStateException, IllegalArgumentException {
		checkStarted();
		if (robots.containsKey(name))
			throw new IllegalArgumentException("Robot alredy exist in game");
		robots.put(name, 
				new Robot(name, team, placer.placeNew(team)));
	}

	@Override
	public State getState() {
		return null;
	}

	@Override
	public double getTime() {
		return time;
	}
	
	@Override
	public PhysicalConstraints getPhysicalConstraints() {
		return constraints;
	}

	@Override
	public void run(double time,
			java.util.Map<String,ControlCommand> commands) {
	}

	@Override
	public void setMap(org.osll.roboracing.world.Map map)
			throws IllegalStateException {
		checkStarted();
		this.map = map;
	}
	
	private void checkStarted() {
		if (time > 0)
			throw new IllegalStateException("Game started");		
	}
}
