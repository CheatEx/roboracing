package org.osll.roboracing.world;

/**
 * It interface for some physical constraints applied to our robots.
 * 
 * @author zan
 */
public interface PhysicalConstraints {
	
	public double getMaxVelocity();
	
	public double getMaxAcceleration();
	
	public double getMaxAngularSpeed();
	
	public double getDragCoefficient();
	
	public double getVisionRadius();
	
	public double getWorldRadius();

}
