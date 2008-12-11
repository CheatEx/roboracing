package org.osll.roboracing.server.game;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.PhysicalConstraints;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Telemetry;

/**
 * Server-private interface for game instance. Designed as thread-safe wrapper
 * around {@link Game}. Intended to serving of robot connectors. Clients should
 * provide name of robot, their present.
 * 
 * @author zan
 */
public interface GameController {

	/**
	 * Starts the game.
	 */
	public void start();
	
	public PhysicalConstraints getConstraints();
	
	public State getGameState();

	public Telemetry getTelemetryFor(String name)
		throws IllegalArgumentException;

	/**
	 * Method for accepting commands. Accumulate commands, that will be sent to
	 * game on next step, during calculation circle, when sending commands to game
	 * impossible.
	 * 
	 * @param name name of robot which must receive command
	 * @param command command for robot
	 */
	public void putCommand(String name, ControlCommand command);
}
