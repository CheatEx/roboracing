package org.osll.roboracing.world;

/**
 * @author zan
 */
public interface ControlCommand {

	/**
	 * This method must return speed derivative.
	 * XXX is it right decision about command character?
	 * XXX where we should make check for constraint conformance?
	 * On client, when filling out implementations of this interface
	 * or on server, when running calculations?
	 * 
	 * @return speed derivative
	 */
	public Speed getSpeedDerivative();
	
	/**
	 * Name of robot who must receive command.
	 * @return
	 */
	public String getRobotName();
}
