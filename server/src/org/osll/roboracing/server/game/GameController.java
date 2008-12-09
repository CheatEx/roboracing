package org.osll.roboracing.server.game;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Telemetry;

/**
 * Server-private interface for game instance. Intended to serving of
 * connectors. Clients should provide name of robot, their present.
 * 
 * @author zan
 */
public interface GameController {

	/**
	 * Starts the game.
	 */
	public void start();
	
	public State getGameState();

	public Telemetry getTelemetryFor(String name);

	/**
	 * Method for accepting commands. Accumulate commands, that will be sent to
	 * game on next step, during calculation circle, when command sending to game
	 * impossible.
	 * 
	 * @param name name of robot which must receive command
	 * @param command command for robot
	 */
	public void putCommand(String name, ControlCommand command);
}
