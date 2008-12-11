package org.osll.roboracing.server.game.controller;

import java.util.Collection;

import org.osll.roboracing.server.game.Game;
import org.osll.roboracing.server.game.GameController;
import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.Robot;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Telemetry;

/**
 * Game controller implementation.
 * 
 * @author zan
 */
public class ControllerImpl implements GameController {

	private Game game;
	
	@Override
	public State getGameState() {
		return game.getState();
	}

	@Override
	public Telemetry getTelemetryFor(String name) {
		State state = getGameState();
		
		Telemetry tel = new Telemetry();
		tel.setTime(game.getTime());
		tel.setSelf(
				findRobot(state.getRobots(), name));
		
		return tel;
	}

	@Override
	public void putCommand(String name, ControlCommand command) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public PhysicalConstraints getConstraints() {
		return game.getPhysicalConstraints();
	}
	
	private Robot findRobot(Collection<Robot> robots, String name) {
		for (Robot robot : robots) {
			if (robot.getName().equals(name))
				return robot;
		}
		throw new IllegalArgumentException("No robot with name "+name+" found");
	}
}
