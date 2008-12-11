package org.osll.roboracing.world;


/**
 * Interface for controlling robot, receive telemetry and general game info.
 *  
 * @author zan
 */
public interface Control {

	/**
	 * This method isn't cheat-safe (:
	 * @param roboName name of robot, who received picture
	 * @return robots telemetry
	 * @throws IllegalStateException if game not
	 */
	public Telemetry getTelemetry() throws IllegalStateException;
	
	/**
	 * And this too :(
	 * @param command command for robot
	 * @throws IllegalStateException if game not
	 */
	public void sendCommand(ControlCommand command) throws IllegalStateException;
	
	public boolean isGameStarted();
	
	public PhysicalConstraints getPhysicalConstraints();
	
	/**
	 * Получить время до старта. Время может меняться.  
	 * @return время в милисекундах до ориентировачного старта  
	 */
	public long getTimeToStart();
}
