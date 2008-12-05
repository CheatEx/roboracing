package org.osll.roboracing.world;

/**
 * @author zan
 */
public interface ControlCommand {

	public double getAcceleration();
	
	public double getAngularSpeed();
	
	/**
	 * Name of robot who must receive command.
	 * @return
	 */
	public String getRobotName();
}
