package org.osll.roboracing.server.game.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	private static TelemetryFactory telemetryFactory =
			TelemetryFactory.instance();
	
	private Map<String, ControlCommand> commands =
			new HashMap<String, ControlCommand>();
	
	private Game game;
	
	/**
	 * Game, that will be controlled. Game must be provided with appropriate map
	 * and added robots.  
	 * @param game The Game
	 */
	public ControllerImpl(Game game) {
		this.game = game;
	}
	
	@Override
	public State getGameState() {
		return game.getState();
	}

	@Override
	public Telemetry getTelemetryFor(String name) {
		State state = getGameState();
		Robot self = findRobot(state.getRobots(), name);
		
		Telemetry tel =	telemetryFactory.createTelemetry(state, 
				new VisionRadiusFilter(self, getConstraints().getVisionRadius()));
		
		tel.setTime(game.getTime());
		tel.setSelf(self);
		
		return tel;
	}

	@Override
	public void putCommand(String name, ControlCommand command) {
		commands.put(name, command);
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
