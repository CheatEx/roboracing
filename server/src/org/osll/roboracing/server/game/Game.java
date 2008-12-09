package org.osll.roboracing.server.game;

import java.util.Collection;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.Map;
import org.osll.roboracing.world.State;
import org.osll.roboracing.world.Team;

/**
 * Its server-private interface for simulation engine.
 * 
 * @author zan
 */
public interface Game {
	
	/**
	 * Adds new robot to game.
	 * @throws IllegalStateException if game already started
	 */
	public void addRobot(String name, Team team) throws IllegalStateException;
	
	/**
	 * Sets map for game.
	 * @param map 
	 * @throws IllegalStateException if game already started
	 */
	public void setMap(Map map) throws IllegalStateException;
	
	public void run(double time, Collection<ControlCommand> commands);
	
	/**
	 * @return current game time
	 */
	public double getTime();

	/**
	 * Snapshot of world at current time.
	 * XXX should return copy of internal state?
	 * @return
	 */
	public State getState();
}
