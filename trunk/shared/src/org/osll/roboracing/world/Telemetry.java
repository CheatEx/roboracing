package org.osll.roboracing.world;

/**
 * @author zan
 */
public interface Telemetry extends State {
	
	/**
	 * Time, when picture fixed.
	 * @return telemetry timestamp
	 */
	public double getTime();
	
	public Robot getSelf();
}
