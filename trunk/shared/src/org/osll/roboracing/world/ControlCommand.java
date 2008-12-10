package org.osll.roboracing.world;

/**
 * @author zan
 */
public class ControlCommand {

	private double acceleration;
	
	private double angularSpeed;
	
	public double getAcceleration() {
		return acceleration;
	}
	
	public double getAngularSpeed() {
		return angularSpeed;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
}
