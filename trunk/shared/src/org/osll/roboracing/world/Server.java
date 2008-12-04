package org.osll.roboracing.world;

/**
 * Its general touchpoint between server and robo clients.
 *  
 * @author zan
 */
public interface Server {

	/**
	 * This method isn't cheat-safe (:
	 * @param roboName name of robot, who received picture
	 * @return robots telemetry
	 */
	public Telemetry getTelemetry(String roboName);
	
	/**
	 * And this too :(
	 * @param command command for robot
	 * @throws IllegalStateException server isn't ready for accepting new command
	 */
	public void sendCommand(ControlCommand command) throws IllegalStateException;
	
	public PhysicalConstraints getPhysicalConstraints();
}
