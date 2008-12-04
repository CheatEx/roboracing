package org.osll.roboracing.server.game;

import java.util.Collection;

import org.osll.roboracing.world.ControlCommand;
import org.osll.roboracing.world.State;

/**
 * Its server-private interface for simulation engine.
 * Initial state must be defined by constructor(s)
 * or additional methods in implementations.
 * 
 * @author zan
 */
public interface Game {
	
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
