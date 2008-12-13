package org.osll.roboracing.world;

import java.io.Serializable;

/**
 */
public class ControlCommand implements Serializable {

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
